package org.uva.sea;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.uva.sea.languages.ql.interpreter.Evaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.ErrorValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(Parameterized.class)
public class QLEvaluatorTest extends TestCase {

    private static final TestFileHelper testFileHelper = new TestFileHelper();
    //Parameters for every test
    private final String testFile;
    private final int correctQuestions;
    private final boolean hasRuntimeError;
    private final boolean hasWarnings;

    /**
     * Constructor for every test
     *
     * @param testFile
     * @param correctQuestions
     */
    public QLEvaluatorTest(String testFile, int correctQuestions, boolean hasRuntimeError, boolean hasWarnings) {
        this.testFile = testFile;
        this.correctQuestions = correctQuestions;
        this.hasRuntimeError = hasRuntimeError;
        this.hasWarnings = hasWarnings;
    }

    /**
     * Test generator
     *
     * @return Test parameters
     */
    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> testFiles = new ArrayList<>();
        testFiles.addAll(QLEvaluatorTest.getTestFiles("src/test/resources/calculateQL/", false, false));
        testFiles.addAll(QLEvaluatorTest.getTestFiles("src/test/resources/runtimeErrorsQl/", true, false));
        testFiles.addAll(QLEvaluatorTest.getTestFiles("src/test/resources/runtimeWarningsQl/", false, true));

        return testFiles;

    }

    /**
     * @param folderLocation Location of the ql files
     * @return Map of test files and if they should be interpretable
     */
    private static Collection<Object[]> getTestFiles(String folderLocation, boolean hasRuntimeError, boolean hasWarnings) {
        Collection<Object[]> testFiles = new ArrayList<>();

        Collection<String> locations = QLEvaluatorTest.testFileHelper.getTestFiles(folderLocation);
        for (String location : locations) {
            testFiles.add(new Object[]{location, QLEvaluatorTest.determineExpectedTests(location), hasRuntimeError, hasWarnings});
        }

        return testFiles;
    }

    /**
     * Extract correct tests from file
     *
     * @param location
     * @return
     */
    private static int determineExpectedTests(String location) {
        try (FileInputStream inputStream = new FileInputStream(location)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String firstLine = reader.readLine();

            Pattern pattern = Pattern.compile("\\/\\/Q=([0-9]*)");
            Matcher matcher = pattern.matcher(firstLine);
            if (matcher.find()) {
                String match = matcher.group(1);
                return Integer.parseInt(match);
            }
        } catch (IOException ignored) {
            return 0;
        }

        return 0;
    }

    /**
     * Extracts the symbol table from the test file
     *
     * @param location Location of the test file
     * @return The Symbol table
     */
    private SymbolTable getSymbolTableForTest(String location) throws ReflectiveOperationException, IOException {

        SymbolTable symbolTable = new SymbolTable();

        FileInputStream inputStream = new FileInputStream(location);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            Pattern pattern = Pattern.compile("\\/\\/([a-zA-Z]+):=([a-zA-Z]+) ([0-9a-zA-Z]+)");
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String variableName = matcher.group(1);
                String variableType = matcher.group(2);
                String variableValue = matcher.group(3);

                Class dynamicClass = Class.forName("org.uva.sea.languages.ql.interpreter.evaluate.valueTypes." + variableType);
                Value value = (Value) dynamicClass.getDeclaredConstructor(String.class).newInstance(variableValue);

                symbolTable.addOrUpdateValue(variableName, value);
            }
        }

        return symbolTable;
    }


    /**
     * Compiles the file and checks result
     *
     * @param fileName The location of the ql file
     * @return If the script is interpretable
     */
    private EvaluationResult getDisplayedQuestions(String fileName) throws IOException, EvaluationException, ReflectiveOperationException {

        SymbolTable symbolTable = this.getSymbolTableForTest(fileName);
        Evaluator qlSpecificationEvaluator = new Evaluator();
        EvaluationResult questions = qlSpecificationEvaluator.evaluate(fileName, symbolTable);

        if (this.checkForRuntimeErrors(questions.getQuestions())) {
            throw new EvaluationException("Exception during evaluation");
        }

        return questions;
    }

    /**
     * Check if there was an error
     *
     * @param questions All the questions
     * @return
     */
    private boolean checkForRuntimeErrors(Iterable<QuestionData> questions) {
        for (QuestionData question : questions) {
            if (question.getValue() == null)
                continue;

            Boolean error = question.getValue().accept(new BaseValueVisitor<Boolean>() {
                public Boolean visit(final ErrorValue node) {
                    return true;
                }
            });
            if ((error != null) && error)
                return true;
        }
        return false;
    }


    @Test
    public void testFile() throws IOException, ReflectiveOperationException {
        try {
            System.out.println("Testing: " + this.testFile);
            EvaluationResult interpreterResult = this.getDisplayedQuestions(this.testFile);

            Assert.assertEquals(this.correctQuestions, interpreterResult.getQuestions().size());
            Assert.assertFalse(this.hasRuntimeError);
            Assert.assertEquals(this.hasWarnings, interpreterResult.getMessages().hasMessagePresent(MessageTypes.WARNING));
        } catch (EvaluationException ignored) {
            Assert.assertTrue(this.hasRuntimeError);
        }
    }
}