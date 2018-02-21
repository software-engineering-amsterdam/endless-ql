package org.uva.sea.ql;

import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.types.Bool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RunWith(Parameterized.class)
public class QLCompilerTest extends TestCase {

    private String testFile;
    private Boolean shouldCompile;


    public QLCompilerTest(String testFile, Boolean shouldCompile) {
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
     *
     * @param folderLocation Location of the QL files
     * @param shouldCompile Should the file compile?
     * @return Map of test files and if they should compile
     */
    private static Collection<Object[]> getTestFiles(String folderLocation, Boolean shouldCompile) {
        Collection<Object[]> testFiles = new ArrayList<Object[]>();
        File folder = new File(folderLocation);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                testFiles.add(new Object[] {file.getAbsolutePath(),shouldCompile});
            }
        }
        return testFiles;
    }

    /**
     * Compiles the file and checks result
     * @param fileName The location of the QL file
     * @return If the script compiles
     */
    private boolean doesCompile(String fileName) {
        try {
            QLCompiler compiler = new QLCompiler();
            CharStream steam = CharStreams.fromStream(new FileInputStream(fileName));
            Form result = compiler.compileScriptFile(steam);
            return result != null;
        } catch (IOException e) {
            return false;
        }
    }


    @Test
    public void testFile() {
        System.out.println("Testing: " + this.testFile);
        Assert.assertEquals(this.shouldCompile, this.doesCompile(this.testFile));
    }
}