package org.uva.jomi.ql.tests.utilities;

import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.ql.ast.AstBuilder;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.parser.antlr.QLLexer;
import org.uva.jomi.ql.parser.antlr.QLParser;
import org.uva.jomi.ql.parser.antlr.QLParser.ParseContext;

public class TestUtilities {

	public static List<Stmt> buildAst(String source) {
		CharStream inputStream = CharStreams.fromString(source);
		// Create a lexer instance
		QLLexer lexer = new QLLexer(inputStream);
		// Create a token stream using the lexer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		// Create a lexer instance
		QLParser parser = new QLParser(tokens);

		ParseContext cst = parser.parse();

		AstBuilder astBuilder = new AstBuilder(false);
		return astBuilder.visit(cst);
	}

}
