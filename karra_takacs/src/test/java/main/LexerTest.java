package main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Assert;
import org.junit.Test;
import qlviz.qlLexer;

import java.util.BitSet;
import java.util.List;


public class LexerTest {

    @Test
    public void testEmptyForm() {
        qlLexer lexer = new qlLexer(new ANTLRInputStream("form { }"));
        List<? extends Token> tokens = lexer.getAllTokens();
        Assert.assertEquals(0, tokens.size());
    }

    @Test
    public void testSingleQuestion() {
        qlLexer lexer = new qlLexer(new ANTLRInputStream(
                "form { " +
                        "hasSoldHouse: \"Did you sell a house in 2010?\" boolean" +
                        "}"));
        List<? extends Token> tokens = lexer.getAllTokens();
        Assert.assertEquals(3, tokens.size());
        Assert.assertTrue(tokens.stream().anyMatch(t -> t.getType() == qlLexer.IDENTIFIER));
        Assert.assertTrue(tokens.stream().anyMatch(t -> t.getType() == qlLexer.STRING));
        Assert.assertTrue(tokens.stream().anyMatch(t -> t.getType() == qlLexer.TYPE));
    }

}
