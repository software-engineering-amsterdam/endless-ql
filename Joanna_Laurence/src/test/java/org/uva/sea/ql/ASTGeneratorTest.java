package org.uva.sea.ql;

import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.uva.sea.ql.dataObject.ASTResult;
import org.uva.sea.ql.exceptions.StaticAnalysisError;

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
     * @param folderLocation Location of the QL files
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
     * @param fileName The location of the QL file
     * @return If the script is interpretable
     */
    private boolean doesInterpreter(String fileName) {
        try {
            ASTGenerator ASTGenerator = new ASTGenerator();
            CharStream steam = CharStreams.fromStream(new FileInputStream(fileName));
            ASTResult result = ASTGenerator.interpreterScriptFile(steam);
            return result.getAST() != null;
        } catch (IOException | StaticAnalysisError e) {
            return false;
        }
    }


    @Test
    public void testFile() {
        System.out.println("Testing: " + this.testFile);
        Assert.assertEquals(this.shouldCompile, this.doesInterpreter(this.testFile));
    }
}