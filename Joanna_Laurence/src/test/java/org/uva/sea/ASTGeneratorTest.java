package org.uva.sea;

import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.ql.interpreter.ASTGenerator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.dataObject.ParseResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ASTGeneratorTest extends TestCase {

    private static TestFileHelper testFileHelper = new TestFileHelper();
    private String testFile;
    private Boolean shouldCompile;


    public ASTGeneratorTest(String testFile, Boolean shouldCompile) {
        this.testFile = testFile;
        this.shouldCompile = shouldCompile;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {

        Collection<Object[]> testFiles = new ArrayList<Object[]>();
        testFiles.addAll(getTestFiles("src/test/resources/correctQL/", true));
        testFiles.addAll(getTestFiles("src/test/resources/incorrectQL/", false));

        return testFiles;
    }

    /**
     * @param folderLocation Location of the ql files
     * @param shouldCompile  Should the file be interpretable?
     * @return Map of test files and if they should be interpretable
     */
    private static Collection<Object[]> getTestFiles(String folderLocation, Boolean shouldCompile) {
        Collection<Object[]> testFiles = new ArrayList<Object[]>();

        Collection<String> locations = testFileHelper.getTestFiles(folderLocation);
        for (String location : locations) {
            testFiles.add(new Object[]{location, shouldCompile});
        }

        return testFiles;
    }

    /**
     * Compiles the file and checks result
     *
     * @param fileName The location of the ql file
     * @return If the script is interpretable
     */
    private boolean doesInterpreter(String fileName) {
        try {
            QlEvaluator qlEvaluator = new QlEvaluator(fileName);
            EvaluationResult evaluationResult = qlEvaluator.getQuestions();
            return !evaluationResult.getMessages().hasMessagePresent(MessageTypes.ERROR);
        } catch (InterruptedException | IOException e) {
            return false;
        }
    }


    @Test
    public void testFile() {
        System.out.println("Testing: " + this.testFile);
        Assert.assertEquals(this.shouldCompile, this.doesInterpreter(this.testFile));
    }
}