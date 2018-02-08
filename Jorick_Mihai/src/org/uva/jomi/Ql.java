package org.uva.jomi;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.uva.jomi.ql.parser.antlr.*;

public class Ql {

	public static void main(String[] args) {
		// Report an error if the the user does not supply a source file
		if (args.length < 1) {
			System.err.println("Usage Ql <script>");
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
			
			parser.parse();
			
		} catch (IOException e) {
			System.err.println("Source file was not found: " + e.getMessage());
		}
		
	}

}

