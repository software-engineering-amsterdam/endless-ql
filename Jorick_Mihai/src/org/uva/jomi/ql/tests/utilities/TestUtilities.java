package org.uva.jomi.ql.tests.utilities;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.ql.ast.AstBuilder;
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ql.parser.antlr.QLLexer;
import org.uva.jomi.ql.parser.antlr.QLParser;
import org.uva.jomi.ql.parser.antlr.QLParser.ParseContext;
import org.uva.jomi.ql.parser.antlr.QLParserErrorListener;

public class TestUtilities {

	public static List<Statement> buildAst(String source) {
		CharStream inputStream = CharStreams.fromString(source);
		// Create a lexer instance
		QLLexer lexer = new QLLexer(inputStream);
		// Create a token stream using the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// Create a lexer instance
		QLParser parser = new QLParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new QLParserErrorListener());

		ParseContext cst = parser.parse();

		if (parser.getNumberOfSyntaxErrors() == 0) {
			AstBuilder astBuilder = new AstBuilder(false);
			return astBuilder.visit(cst);
		}

		return null;
	}

}
