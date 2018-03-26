package org.uva.sea;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.uva.sea.languages.QlEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QLParserTest extends TestCase {

    private static final TestFileHelper testFileHelper = new TestFileHelper();
    private final String testFile;
    private final Boolean shouldCompile;


    public QLParserTest(String testFile, Boolean shouldCompile) {
        this.testFile = testFile;
        this.shouldCompile = shouldCompile;
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {

        Collection<Object[]> testFiles = new ArrayList<>();
        testFiles.addAll(QLParserTest.getTestFiles("src/test/resources/correctQL/", true));
        testFiles.addAll(QLParserTest.getTestFiles("src/test/resources/incorrectQL/", false));

        return testFiles;
    }

    private static Collection<Object[]> getTestFiles(String folderLocation, Boolean shouldCompile) {
        Collection<Object[]> testFiles = new ArrayList<>();

        Collection<String> locations = QLParserTest.testFileHelper.getTestFiles(folderLocation);
        for (String location : locations) {
            testFiles.add(new Object[]{location, shouldCompile});
        }

        return testFiles;
    }


    private boolean doesInterpreter(String fileName) {
        try {
            QlEvaluator qlEvaluator = new QlEvaluator(fileName);
            EvaluationResult evaluationResult = qlEvaluator.evaluate();
            return !evaluationResult.getMessages().hasMessagePresent(MessageTypes.ERROR);
        } catch (InterruptedException | IOException ignored) {
            return false;
        }
    }


    @Test
    public void testFile() {
        System.out.println("Testing: " + this.testFile);
        Assert.assertEquals(this.shouldCompile, this.doesInterpreter(this.testFile));
    }
}