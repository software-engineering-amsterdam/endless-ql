package qlviz;

import org.antlr.v4.runtime.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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
		int j = 0;
		List<Integer> tokenList = new ArrayList<Integer>();
		QLLexer lexer = new QLLexer(
				new ANTLRInputStream("form { " + "\"Did you sell a house in 2010?\" hasSoldHouse: boolean" + "}"));
		tokenList.add(QLLexer.STRING);
		tokenList.add(QLLexer.IDENTIFIER);
		tokenList.add(QLLexer.TYPE);
		List<? extends Token> tokens = lexer.getAllTokens();
		Assert.assertEquals(3, tokens.size());
		//matches the type of tokens
		for (Token token : tokens) {
			while (j < tokenList.size()) {
				Assert.assertEquals(token.getType(), tokenList.get(j).intValue());
				break;
			}
			j++;
		}
	}

}
