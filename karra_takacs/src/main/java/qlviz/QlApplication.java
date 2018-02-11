package qlviz;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class QlApplication {
	public static void main(String[] args) {
		CharStream cs = null;
		String file = "C:\\projects\\endless-ql\\karra_takacs\\src\\main\\resources\\taxform.QL";
		try {
			// reads the file into a character stream
			cs = new ANTLRFileStream(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// creates a lexer object
		qlLexer lexer = new qlLexer(cs);
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
