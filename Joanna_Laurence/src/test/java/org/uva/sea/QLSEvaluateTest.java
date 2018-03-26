package org.uva.sea;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.uva.sea.languages.QlSEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.MessageTypes;
import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QLSEvaluateTest extends TestCase {

    private static final TestFileHelper testFileHelper = new TestFileHelper();
    private final String testFile;
    private final Boolean shouldCompile;

    public QLSEvaluateTest(String testFile, Boolean shouldCompile) {
        this.testFile = testFile;
        this.shouldCompile = shouldCompile;
    }

    @Parameters(name = "{index}: {0}")
    public static Collection<Object[]> data() {

        Collection<Object[]> testFiles = new ArrayList<>();
        testFiles.addAll(QLSEvaluateTest.getTestFiles("src/test/resources/correctQLS/", true));
        testFiles.addAll(QLSEvaluateTest.getTestFiles("src/test/resources/incorrectQLS/", false));

        return testFiles;
    }


    private static Collection<Object[]> getTestFiles(String folderLocation, Boolean shouldCompile) {
        Collection<Object[]> testFiles = new ArrayList<>();

        Collection<String> locations = QLSEvaluateTest.testFileHelper.getTestFiles(folderLocation);
        for (String location : locations) {
            if(!location.endsWith(".qls"))
                continue;

            testFiles.add(new Object[]{location, shouldCompile});
        }

        return testFiles;
    }

    private boolean doesInterpreter(String fileName) {
        try {
            QlSEvaluator qlsEvaluator = new QlSEvaluator(fileName.replace(".qls",".ql"), fileName);
            EvaluationResult evaluationResult = qlsEvaluator.evaluate();
            Messages evaluationMessages = evaluationResult.getMessages();
            return !evaluationMessages.hasMessagePresent(MessageTypes.ERROR);
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