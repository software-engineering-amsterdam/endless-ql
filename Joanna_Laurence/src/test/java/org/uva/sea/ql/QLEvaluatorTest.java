package org.uva.sea.ql;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.evaluate.FormEvaluator;
import org.uva.sea.ql.evaluate.SymbolTable;
import org.uva.sea.ql.value.ErrorValue;
import org.uva.sea.ql.value.Value;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(Parameterized.class)
public class QLEvaluatorTest extends TestCase {

    //Parameters for every test
    private String testFile;
    private int correctQuestions;

    private static TestFileHelper testFileHelper = new TestFileHelper();
    private FormEvaluator formEvaluator = new FormEvaluator();


    /**
     * Constructor for every test
     * @param testFile
     * @param correctQuestions
     */
    public QLEvaluatorTest(String testFile, int correctQuestions) {
        this.testFile = testFile;
        this.correctQuestions = correctQuestions;
    }

    /**
     * Test generator
     * @return Test parameters
     */
    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {
        return new ArrayList<>(getTestFiles("src/test/resources/calculateQL/"));
    }

    /**
     *
     * @param folderLocation Location of the QL files
     * @return Map of test files and if they should compile
     */
    private static Collection<Object[]> getTestFiles(String folderLocation) {
        Collection<Object[]> testFiles = new ArrayList<Object[]>();

        Collection<String> locations = testFileHelper.getTestFiles(folderLocation);
        for(String location : locations) {
            testFiles.add(new Object[] {location, determineExpectedTests(location)});
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Cannot find number of tests in: " + location);
            e.printStackTrace();
        }

        return 0;
    }


    /**
     * Compiles the file and checks result
     * @param fileName The location of the QL file
     * @return If the script compiles
     */
    private int getDisplayedQuestions(String fileName) throws IOException {

        try {
            SymbolTable symbolTable = this.getSymbolTableForTest(fileName);
            QLFormGenerator qlFormGenerator = new QLFormGenerator();
            List<QuestionData> questions = qlFormGenerator.generate(fileName, symbolTable);

            if(checkForErrors(questions))
                return 0;

            return questions.size();
        } catch (Errors errors) {
            return 0;
        }
    }

    /**
     * Check if there was an error
     * @param questions All the questions
     * @return
     */
    private boolean checkForErrors(List<QuestionData> questions) {
        for(QuestionData question : questions) {
            if(question.getValue() == null)
                continue;

            Boolean error = question.getValue().accept(new QLValueEvaluator<Boolean>() {
                public Boolean visit(ErrorValue node) {
                    return true;
                }
            });
            if(error != null && error)
                return true;
        }
        return false;
    }

    /**
     * Extracts the symbol table from the test file
     * @param location Location of the test file
     * @return The Symbol table
     */
    private SymbolTable getSymbolTableForTest(String location) {

        SymbolTable symbolTable = new SymbolTable();

        try(FileInputStream inputStream = new FileInputStream(location)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\/\\/([a-zA-Z]+):=([a-zA-Z]+) ([0-9a-zA-Z]+)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String variableName = matcher.group(1);
                    String variableType = matcher.group(2);
                    String variableValue = matcher.group(3);

                    Class dynamicClass = Class.forName("org.uva.sea.ql.value." + variableType);
                    Value value = (Value)dynamicClass.getDeclaredConstructor(String.class).newInstance(variableValue);

                    symbolTable.addOrUpdateValue(variableName, value);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Cannot extract variable locations: " + location);
            e.printStackTrace();
        }

        return symbolTable;
    }

    @Test
    public void testFile() throws IOException {
        System.out.println("Testing: " + this.testFile);
        Assert.assertEquals(this.correctQuestions, this.getDisplayedQuestions(this.testFile));
    }
}