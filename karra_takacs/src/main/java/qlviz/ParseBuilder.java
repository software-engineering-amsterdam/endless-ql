package qlviz;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ParseBuilder {
	private QLParser questionParser;

	public ParseTree generateParseTree() {
		CharStream charStream = null;
		try {
			charStream = new FileReader("C:\\\\projects\\\\endless-ql\\\\karra_takacs\\\\src\\\\main\\\\resources\\\\taxform.QL").getStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		QLLexer lexer = new QLLexer(charStream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);
		return parser.question();
	}

	public QLParser getQuestionParser() {
		return questionParser;
	}

}
