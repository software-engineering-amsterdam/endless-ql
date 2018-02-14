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
			charStream = charStreamProvider.getStream();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		qlLexer lexer = new qlLexer(charStream);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		qlParser parser = new qlParser(tokens);


		ParseTreeWalker TreeWalker = new ParseTreeWalker();
		TaxFormBaseListener listener = new TaxFormBaseListener();
		TreeWalker.walk(listener, parser.questionName());

	}

}
