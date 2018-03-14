package org.uva.jomi.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.jomi.ql.ast.AstBuilder;
import org.uva.jomi.ql.ast.analysis.CyclicDependencyChecker;
import org.uva.jomi.ql.ast.analysis.DuplicatedLabelChecker;
import org.uva.jomi.ql.ast.analysis.IdentifierMapBuilder;
import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.analysis.TypeResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.parser.antlr.QLLexer;
import org.uva.jomi.ql.parser.antlr.QLParser;
import org.uva.jomi.ql.parser.antlr.QLParser.ParseContext;
import org.uva.jomi.ui.elements.ElementBuilder;
import org.uva.jomi.ui.elements.core.Frame;
import org.uva.jomi.ui.elements.core.Panel;

public class QLForm {

	private QLParser parser;
	private List<Stmt> ast;

	public QLForm(String filePath) {
		try {
			// Create a character stream form the source file.
			CharStream inputStream = CharStreams.fromFileName(filePath);
			// Create a lexer instance.
			QLLexer lexer = new QLLexer(inputStream);
			// Create a token stream using the lexer.
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			this.parser = new QLParser(tokens);

			ParseContext cst = parser.parse();

			this.ast = new AstBuilder().visit(cst);
		} catch(IOException e) {
			System.err.println("Source file was not found: " + e.getMessage());
		}
	}

	public List<Panel> getPanels() {
		if(this.hasErrors()) {
			// Create error panels with messages
			return new ArrayList<Panel>();
		}
		ElementBuilder builder = new ElementBuilder();
		return builder.build(this.ast);
	}

	private boolean hasErrors() {
		return (this.numberOfSyntaxErrors() > 0) ||
				(this.numberOfCyclicErrors() > 0) ||
				(this.numberOfIdentifierErrors() > 0) ||
				(this.numberOfTypeResolverErrors() > 0);
	}

	private int numberOfSyntaxErrors() {
		return parser.getNumberOfSyntaxErrors();
	}

	private int numberOfCyclicErrors() {
		// Check for cyclic references between questions.
		CyclicDependencyChecker cyclicChecker = new CyclicDependencyChecker(true);

		// Build an identifier map instance that will be used to generate a mapping bewteen questions.
		IdentifierMapBuilder identifierMap = new IdentifierMapBuilder();

		// Build the identifier map and check for cycles.
		cyclicChecker.check(identifierMap.buildMap(this.ast));

		return cyclicChecker.getNumberofErrors();
	}

	private int numberOfIdentifierErrors() {
		IdentifierResolver identifierResolver = new IdentifierResolver(true);
		identifierResolver.resolve(this.ast);

		return identifierResolver.getNumberOfErrors();
	}

	private int numberOfTypeResolverErrors() {
		TypeResolver typeResolver = new TypeResolver(true);
		typeResolver.resolve(this.ast);
		return typeResolver.getNumberOfErrors();
	}

	private int numberOfDuplicatedLabelErrors() {
		DuplicatedLabelChecker labelChecker = new DuplicatedLabelChecker(true);
		labelChecker.check(ast);
		return labelChecker.getNumberOfReports();
	}

}
