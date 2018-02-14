package main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLLexer;
import qlviz.QLLexer;

import java.util.BitSet;
import java.util.List;


public class LexerTest {

    @Test
    public void testEmptyForm() {
        QLLexer lexer = new QLLexer(new ANTLRInputStream("form { }"));
        List<? extends Token> tokens = lexer.getAllTokens();
        Assert.assertEquals(0, tokens.size());
    }

    @Test
    public void testSingleQuestion() {
        QLLexer lexer = new QLLexer(new ANTLRInputStream(
                "form { " +
                        "hasSoldHouse: \"Did you sell a house in 2010?\" boolean" +
                        "}"));
        List<? extends Token> tokens = lexer.getAllTokens();
        Assert.assertEquals(3, tokens.size());
        Assert.assertTrue(tokens.stream().anyMatch(t -> t.getType() == QLLexer.IDENTIFIER));
        Assert.assertTrue(tokens.stream().anyMatch(t -> t.getType() == QLLexer.STRING));
        Assert.assertTrue(tokens.stream().anyMatch(t -> t.getType() == QLLexer.TYPE));
    }

}
