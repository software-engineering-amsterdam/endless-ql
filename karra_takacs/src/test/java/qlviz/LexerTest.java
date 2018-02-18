package qlviz;

import com.google.common.collect.Lists;
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
		tokenList.add(QLLexer.FORM_HEADER);
		tokenList.add(QLLexer.BRACKET_OPEN);
		tokenList.add(QLLexer.STRING);
		tokenList.add(QLLexer.IDENTIFIER);
		tokenList.add(QLLexer.QUESTION_DELIMITER);
		tokenList.add(QLLexer.TYPE);
		tokenList.add(QLLexer.BRACKET_CLOSE);
		List<? extends Token> tokens = lexer.getAllTokens();
		Assert.assertEquals(7, tokens.size());

		for (int i = 0; i < tokens.size(); i++) {
			Assert.assertEquals(tokenList.get(i), Integer.valueOf(tokens.get(i).getType()));
		}
	}


	@Test
	public void testComputedQuestion() {
		int j = 0;
		List<Integer> tokenList = new ArrayList<Integer>();
		QLLexer lexer = new QLLexer(
				new ANTLRInputStream("form { " + "\"Did you sell a house in 2010?\" hasSoldHouse: boolean = (a - b)" + "}"));
		tokenList.addAll(Lists.newArrayList(
				QLLexer.FORM_HEADER,
				QLLexer.BRACKET_OPEN,
				QLLexer.STRING,
				QLLexer.IDENTIFIER,
				QLLexer.QUESTION_DELIMITER,
				QLLexer.TYPE,
				QLLexer.T__0,
				QLLexer.PAREN_OPEN,
				QLLexer.IDENTIFIER,
				QLLexer.BINARY_NUMERIC_OPERATOR,
				QLLexer.IDENTIFIER,
				QLLexer.PAREN_CLOSE,
				QLLexer.BRACKET_CLOSE));
		List<? extends Token> tokens = lexer.getAllTokens();
		Assert.assertEquals(13, tokens.size());

		for (int i = 0; i < tokens.size(); i++) {
			Assert.assertEquals(tokenList.get(i), Integer.valueOf(tokens.get(i).getType()));
		}
	}
}
