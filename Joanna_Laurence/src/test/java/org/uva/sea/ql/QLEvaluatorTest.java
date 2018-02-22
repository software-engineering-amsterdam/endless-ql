package org.uva.sea.ql;

import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.uva.sea.ql.evaluate.Evaluator;
import org.uva.sea.ql.evaluate.EvaluatorBoolean;
import org.uva.sea.ql.evaluate.EvaluatorDecimal;
import org.uva.sea.ql.evaluate.EvaluatorInteger;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.parser.elements.Question;
import sun.misc.IOUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(Parameterized.class)
public class QLEvaluatorTest extends TestCase {

    private String testFile;
    private int correctQuestions;

    private static TestFileHelper testFileHelper = new TestFileHelper();
    private Map<NodeType, Evaluator> evaluators = new HashMap<>();

    public QLEvaluatorTest(String testFile, int correctQuestions) {
        this.testFile = testFile;
        this.correctQuestions = correctQuestions;

        evaluators.put(NodeType.BOOLEAN, new EvaluatorBoolean());
        evaluators.put(NodeType.DECIMAL, new EvaluatorDecimal());
        evaluators.put(NodeType.INTEGER, new EvaluatorInteger());

    }

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

        //TODO: extract bag of variables @ evaluator
        //waitDays:=integer 5
        //waitDays has the value Integer("5")

        Collection<String> locations = testFileHelper.getTestFiles(folderLocation);
        for(String location : locations) {
            testFiles.add(new Object[] {location, determineExpectedTests(location)});
        }

        return testFiles;
    }

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
    private int getDisplayedQuestions(String fileName) {
        try {
            QLCompiler compiler = new QLCompiler();
            CharStream steam = CharStreams.fromStream(new FileInputStream(fileName));
            Form result = compiler.compileScriptFile(steam);
            if(result == null)
                return 0;

            QLEvaluator evaluate = new QLEvaluator(this.evaluators, new HashMap<>());
            List<Question> questions = evaluate.getQuestions(result);
            return questions.size();
        } catch (IOException e) {
            return 0;
        }
    }

    @Test
    public void testFile() {
        System.out.println("Testing: " + this.testFile);
        Assert.assertEquals(this.correctQuestions, this.getDisplayedQuestions(this.testFile));
    }
}