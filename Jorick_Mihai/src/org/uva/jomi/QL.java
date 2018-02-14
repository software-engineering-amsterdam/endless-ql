package org.uva.jomi;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.ql.ast.AstBuilder;
import org.uva.jomi.ql.ast.*;
import org.uva.jomi.ql.parser.antlr.*;

public class QL {

	public static void main(String[] args) {
		// Report an error if the the user does not supply a source file.
		if (args.length < 1) {
			System.err.println("Usage QL <script>");
			System.exit(1);
		}
		
		try {
			// Create a character stream form the source file
			CharStream inputStream = CharStreams.fromFileName(args[0]);
			// Create a lexer instance
			QLLexer lexer = new QLLexer(inputStream);
			// Create a token stream using the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// Create a lexer instance
			QLParser parser = new QLParser(tokens);
			
			AstBuilder astBuilder = new AstBuilder();
			
			List<Stmt> ast = astBuilder.visit(parser.parse());
			
			// Make sure there are no parsing errors before we use the Ast.
			// TODO - Extend the Antlr lexer in order to identify if lexical errors occurred.
			if (parser.getNumberOfSyntaxErrors() == 0) {
				
				// Output the Ast in Graphviz dot format.
				java.io.PrintStream outStream = new java.io.PrintStream("graph.txt");
				outStream.println(new AstGraph().getGraph(ast));
				outStream.close();
			}
			
		} catch (IOException e) {
			System.err.println("Source file was not found: " + e.getMessage());
		}
	}

}

