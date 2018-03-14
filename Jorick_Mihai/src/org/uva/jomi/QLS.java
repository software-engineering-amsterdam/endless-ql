package org.uva.jomi;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.qls.ast.AstBuilder;
import org.uva.jomi.qls.ast.statements.Stmt;
import org.uva.jomi.qls.parser.antlr.QLSLexer;
import org.uva.jomi.qls.parser.antlr.QLSParser;
import org.uva.jomi.qls.parser.antlr.QLSParser.ParseContext;

public class QLS {

	public static void main(String[] args) {
		try {
			// Report an error if the the user does not supply a source file.
			if (args.length < 1) {
				System.err.println("Usage QLS <script>");
				System.exit(1);
			}
			
			// Create a character stream form the source file.
			CharStream inputStream = CharStreams.fromFileName(args[0]);
			// Create a lexer instance.
			QLSLexer lexer = new QLSLexer(inputStream);
			// Create a token stream using the lexer.
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// Create a lexer instance.
			QLSParser parser = new QLSParser(tokens);

			ParseContext cst = parser.parse();

			AstBuilder astBuilder = new AstBuilder();
			List<Stmt> ast = astBuilder.visit(cst);
			
			System.out.println(ast);
		} catch (IOException e) {
			System.err.println("Source file was not found: " + e.getMessage());
		}
	}

}
