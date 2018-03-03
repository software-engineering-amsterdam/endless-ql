package org.uva.sea.ql;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.exceptions.StaticAnalysisError;
import org.uva.sea.ql.evaluate.valueTypes.ErrorValue;
import org.uva.sea.ql.evaluate.valueTypes.Value;
import org.uva.sea.ql.visitor.BaseValueVisitor;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(Parameterized.class)
public class QLEvaluatorTest extends TestCase {

    //Parameters for every test
    private String testFile;
    private int correctQuestions;
    private boolean hasRuntimeError;

    private static TestFileHelper testFileHelper = new TestFileHelper();

    /**
     * Constructor for every test
     * @param testFile
     * @param correctQuestions
     */
    public QLEvaluatorTest(String testFile, int correctQuestions, boolean hasRuntimeError) {
        this.testFile = testFile;
        this.correctQuestions = correctQuestions;
        this.hasRuntimeError = hasRuntimeError;
    }

    /**
     * Test generator
     * @return Test parameters
     */
    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        Collection<Object[]> testFiles = new ArrayList<Object[]>();
        testFiles.addAll(getTestFiles("src/test/resources/calculateQL/", false));
        testFiles.addAll(getTestFiles("src/test/resources/runtimeErrorsQl/", true));
        return testFiles;

    }

    /**
     *
     * @param folderLocation Location of the QL files
     * @return Map of test files and if they should compile
     */
    private static Collection<Object[]> getTestFiles(String folderLocation, boolean hasRuntimeError) {
        Collection<Object[]> testFiles = new ArrayList<Object[]>();

        Collection<String> locations = testFileHelper.getTestFiles(folderLocation);
        for(String location : locations) {
            testFiles.add(new Object[] {location, determineExpectedTests(location), hasRuntimeError});
        }

        return testFiles;
    }

    /**
     * Extract correct tests from file
     * @param location
     * @return
     */
    private static int determineExpectedTests(String location) {
        try(FileInputStream inputStream = new FileInputStream(location)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String firstLine = reader.readLine();

            Pattern pattern = Pattern.compile("\\/\\/Q=([0-9]*)");
            Matcher matcher = pattern.matcher(firstLine);
            if (matcher.find()) {
                String match = matcher.group(1);
                return Integer.parseInt(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Extracts the symbol table from the test file
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

                Class dynamicClass = Class.forName("org.uva.sea.ql.evaluate.valueTypes." + variableType);
                Value value = (Value)dynamicClass.getDeclaredConstructor(String.class).newInstance(variableValue);

                symbolTable.addOrUpdateValue(variableName, value);
            }
        }

        return symbolTable;
    }


    /**
     * Compiles the file and checks result
     * @param fileName The location of the QL file
     * @return If the script compiles
     */
    private int getDisplayedQuestions(String fileName) throws IOException, RuntimeException, StaticAnalysisError, ReflectiveOperationException {

        SymbolTable symbolTable = this.getSymbolTableForTest(fileName);
        QLSpecificationEvaluator qlSpecificationEvaluator = new QLSpecificationEvaluator();
        List<QuestionData> questions = qlSpecificationEvaluator.generate(fileName, symbolTable);

        if(checkForRuntimeErrors(questions)) {
            throw new RuntimeException();
        }

        return questions.size();
    }

    /**
     * Check if there was an error
     * @param questions All the questions
     * @return
     */
    private boolean checkForRuntimeErrors(List<QuestionData> questions) {
        for(QuestionData question : questions) {
            if(question.getValue() == null)
                continue;

            Boolean error = question.getValue().accept(new BaseValueVisitor<Boolean>() {
                public Boolean visit(ErrorValue node) {
                    return true;
                }
            });
            if(error != null && error)
                return true;
        }
        return false;
    }


    @Test
    public void testFile() throws IOException, ReflectiveOperationException, StaticAnalysisError {
        try {
            System.out.println("Testing: " + this.testFile);
            Assert.assertEquals(this.correctQuestions, this.getDisplayedQuestions(this.testFile));
            Assert.assertEquals(this.hasRuntimeError, false);
        } catch (RuntimeException e) {
            Assert.assertEquals(this.hasRuntimeError, true);
        }
    }
}