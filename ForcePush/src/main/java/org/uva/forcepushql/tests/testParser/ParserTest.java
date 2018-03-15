package org.uva.forcepushql.tests.testParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.junit.Assert;
import org.junit.Test;
import org.uva.forcepushql.antlr.GrammarLexer;
import org.uva.forcepushql.antlr.GrammarParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;


public class ParserTest {

    public GrammarLexer lexerForCode (String code) throws IOException {
        return new GrammarLexer(new ANTLRInputStream(new StringReader(code)));
    }

    public GrammarLexer lexerForResource (String resourceName) throws IOException {
        GrammarLexer gl = new GrammarLexer(new ANTLRInputStream(new FileInputStream("src\\main\\java\\org\\uva\\forcepushql\\tests\\testParser\\" + resourceName)));
        System.out.println(gl._input);
        return gl;
    }


    public GrammarParser parse (GrammarLexer lexer){
        return new GrammarParser(new CommonTokenStream(lexer));
    }

    public GrammarParser.QuestionFormatContext parseResource(String resourceName) throws IOException {
        GrammarLexer gl = lexerForResource(resourceName);
        System.out.println(gl.getRuleNames());
        return new GrammarParser(new CommonTokenStream(gl)).questionFormat();

    }

    @Test
    public void parseQuestionAssignment() throws IOException {
        RepresentationOfAST test = new RepresentationOfAST();
        Assert.assertEquals(
                "QuestionFormat" +
                        "   variable" +
                        "       T[hasSoldHouse]" +
                        "   T[\"Did you sell a house in 2010 ?\"]" +
                        "   T[:]" +
                        "   type" +
                        "       T[boolean]", test.toParseTree(parseResource("question_assignment.txt")));

    }

}
