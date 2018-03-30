package org.uva.forcepushql.tests.testExpressions;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.uva.forcepushql.parser.antlr.GrammarLexer;
import org.uva.forcepushql.parser.antlr.GrammarParser;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class UnitTests
{


    File testFile1 = new File("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput1.txt");
    File testFile2 = new File("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput2.txt");
    File testFile3 = new File("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput3.txt");
    File testFile4 = new File("src/main/java/org/uva/forcepushql/tests/testExpressions/testInput4.txt");

    private InputStream stream1 = new FileInputStream(testFile1);

    private ANTLRInputStream input = new ANTLRInputStream(stream1);

    private GrammarLexer lexer = new GrammarLexer(input);

    private CommonTokenStream tokens = new CommonTokenStream(lexer);

    private GrammarParser parser = new GrammarParser(tokens);

    public UnitTests() throws IOException
    {
    }

    @Test
    public void testExpressions() throws IOException
    {

    }
}
