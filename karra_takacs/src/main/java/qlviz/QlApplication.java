package qlviz;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QlApplication {
	public static void main(String[] args) {
		ICharStreamProvider charStreamProvider = new FileReader(args[0]);
		CharStream charStream;
		try {
			// reads the file into a character stream
			charStream = charStreamProvider.getStream();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		// creates a lexer object
		qlLexer lexer = new qlLexer(charStream);
		// List<? extends Token> allTokens = lexer.getAllTokens();
		// gets the tokens which match the lexer.
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// pass the list of tokens to the parsers.
		qlParser parser = new qlParser(tokens);

		// Attaching the walk to the listener
		ParseTreeWalker TreeWalker = new ParseTreeWalker();
		TaxFormBaseListener listener = new TaxFormBaseListener();
		TreeWalker.walk(listener, parser.questionName());

	}

}
