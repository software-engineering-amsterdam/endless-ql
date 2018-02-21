package qlviz.gui;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import qlviz.QLLexer;
import qlviz.QLParser;

public class ParserBuilder {
	private QLParser questionParser;

	public ParseTree generateParseTree(String path) {
		CharStream charStream = null;
		try {
			charStream = new FileReader(path).getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		QLLexer lexer = new QLLexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);
		return parser.form();
	}

	public QLParser getQuestionParser() {
		return questionParser;
	}

}
