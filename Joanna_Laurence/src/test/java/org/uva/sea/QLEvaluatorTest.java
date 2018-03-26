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
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.ErrorValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.exceptions.EvaluationException;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public QLEvaluatorTest(final String testFile, final int correctQuestions, final boolean hasRuntimeError, final boolean hasWarnings) {
        this.testFile = testFile;
        this.correctQuestions = correctQuestions;
        this.hasRuntimeError = hasRuntimeError;
        this.hasWarnings = hasWarnings;
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        final Collection<Object[]> testFiles = new ArrayList<>();
        testFiles.addAll(QLEvaluatorTest.getTestFiles("src/test/resources/calculateQL/", false, false));
        testFiles.addAll(QLEvaluatorTest.getTestFiles("src/test/resources/runtimeErrorsQl/", true, false));
        testFiles.addAll(QLEvaluatorTest.getTestFiles("src/test/resources/runtimeWarningsQl/", false, true));

        return testFiles;

    }

    private static Collection<Object[]> getTestFiles(final String folderLocation, final boolean hasRuntimeError, final boolean hasWarnings) {
        final Collection<Object[]> testFiles = new ArrayList<>();

        final Collection<String> locations = QLEvaluatorTest.testFileHelper.getTestFiles(folderLocation);
        for (final String location : locations) {
            testFiles.add(new Object[]{location, QLEvaluatorTest.determineExpectedTests(location), hasRuntimeError, hasWarnings});
        }

        return testFiles;
    }

    private static int determineExpectedTests(final String location) {
        try (FileInputStream inputStream = new FileInputStream(location)) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final String firstLine = reader.readLine();

            final Pattern pattern = Pattern.compile("\\/\\/AssertDisplayedQuestions=([0-9]*)");
            final Matcher matcher = pattern.matcher(firstLine);
            if (matcher.find()) {
                final String match = matcher.group(1);
                return Integer.parseInt(match);
            }
        } catch (final IOException ignored) {
            return 0;
        }

        return 0;
    }

    private SymbolTable getSymbolTableForTest(final String location) throws ReflectiveOperationException, IOException {

        final SymbolTable symbolTable = new SymbolTable();

        final FileInputStream inputStream = new FileInputStream(location);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            final Pattern pattern = Pattern.compile("\\/\\/([a-zA-Z]+):=([a-zA-Z]+) ([0-9a-zA-Z]+)");
            final Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                final String variableName = matcher.group(1);
                final String variableType = matcher.group(2);
                final String variableValue = matcher.group(3);

                final Class dynamicClass = Class.forName("org.uva.sea.languages.ql.interpreter.evaluate.valueTypes." + variableType);
                final Value value = (Value) dynamicClass.getDeclaredConstructor(String.class).newInstance(variableValue);

                symbolTable.addOrUpdateValue(variableName, value);
            }
        }

        return symbolTable;
    }

    private EvaluationResult getDisplayedQuestions(final String fileName) throws IOException, EvaluationException, ReflectiveOperationException {

        final SymbolTable symbolTable = this.getSymbolTableForTest(fileName);
        final Evaluator qlSpecificationEvaluator = new Evaluator();
        final EvaluationResult questions = qlSpecificationEvaluator.evaluate(fileName, symbolTable);

        if (this.checkForRuntimeErrors(questions.getQuestions())) {
            throw new EvaluationException("Exception during evaluation");
        }

        return questions;
    }

    private boolean checkForRuntimeErrors(final Iterable<QuestionData> questions) {
        for (final QuestionData question : questions) {
            if (question.getValue() == null)
                continue;

            final Boolean error = question.getValue().accept(new BaseValueVisitor<Boolean>() {
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
    public final void testFile() throws IOException, ReflectiveOperationException {
        try {
            System.out.println("Testing: " + this.testFile);
            final EvaluationResult interpreterResult = this.getDisplayedQuestions(this.testFile);

            Assert.assertEquals(this.correctQuestions, interpreterResult.getQuestions().size());
            Assert.assertFalse(this.hasRuntimeError);
            Assert.assertEquals(this.hasWarnings, interpreterResult.getMessages().hasMessagePresent(MessageTypes.WARNING));
        } catch (final EvaluationException ignored) {
            Assert.assertTrue(this.hasRuntimeError);
        }
    }
}