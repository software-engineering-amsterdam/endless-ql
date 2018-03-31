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
import org.uva.jomi.ql.ast.statements.Statement;
import org.uva.jomi.ql.parser.antlr.QLLexer;
import org.uva.jomi.ql.parser.antlr.QLParser;
import org.uva.jomi.ql.parser.antlr.QLParser.ParseContext;
import org.uva.jomi.ql.parser.antlr.QLParserErrorListener;
import org.uva.jomi.ui.elements.ElementBuilder;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.elements.panel.ErrorPanel;
import org.uva.jomi.ui.elements.panel.PanelElement;

public class QLForm {

	private QLParser parser;
	private QLParserErrorListener parserErrorListener;
	private List<Statement> ast;

	public QLForm(String filePath) {
		try {
			// Create a character stream form the source file.
			CharStream inputStream = CharStreams.fromFileName(filePath);
			// Create a lexer instance.
			QLLexer lexer = new QLLexer(inputStream);
			// Create a token stream using the lexer.
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			this.parser = new QLParser(tokens);

			// Create a instance of the custom error listener.
			parserErrorListener = new QLParserErrorListener();
			this.parser.removeErrorListeners();
			this.parser.addErrorListener(parserErrorListener);

			ParseContext cst = parser.parse();

			if (parser.getNumberOfSyntaxErrors() == 0) {
				AstBuilder builder = new AstBuilder(true);
				this.ast = builder.visit(cst);

				if (builder.getNumberOfErros() > 0) {
					System.err.println("Failed to build the AST");
				}
			}

		} catch(IOException e) {
			System.err.println("Source file was not found: " + e.getMessage());
		}
	}

	public List<Panel> getPanels() {
		if(this.hasErrors()) {
			List<Panel> panels = new ArrayList<>();
			panels.add(this.errorPanels());
			return panels;
		}
		ElementBuilder builder = new ElementBuilder();
		return builder.build(this.ast);
	}

	private Panel errorPanels() {
		PanelElement panel = new PanelElement();

		if(this.hasErrors()) {
			panel.addElement(new ErrorPanel(this.getErrors()));
		}

		return panel.build();
	}

	private List<String> getErrors() {
		List<String> errors = new ArrayList<String>();

		// Make sure there are no parsing errors.
		if (parser.getNumberOfSyntaxErrors() > 0) {
			errors.addAll(this.errorsOfParser());
		} else {
			errors.addAll(this.errorsOfCyclicDependency());
			errors.addAll(this.errorsOfIdentifier());
			errors.addAll(this.errorsOfTypeResolver());
			errors.addAll(this.errorsOfDuplicatedLabel());
		}

		return errors;
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

	private List<String> errorsOfParser() {
		return parserErrorListener.getErrors();
	}


	private List<String> errorsOfCyclicDependency() {
		// Check for cyclic references between questions.
		CyclicDependencyChecker cyclicChecker = new CyclicDependencyChecker();

		// Build an identifier map instance that will be used to generate a mapping between questions.
		IdentifierMapBuilder identifierMap = new IdentifierMapBuilder();

		// Build the identifier map and check for cycles.
		cyclicChecker.check(identifierMap.buildMap(this.ast));

		return cyclicChecker.getErrors();
	}

	private int numberOfCyclicErrors() {
		return this.errorsOfCyclicDependency().size();
	}

	private List<String> errorsOfIdentifier() {
		IdentifierResolver identifierResolver = new IdentifierResolver();
		identifierResolver.resolve(this.ast);
		return identifierResolver.getErrors();
	}

	private int numberOfIdentifierErrors() {
		return this.errorsOfIdentifier().size();
	}

	private List<String> errorsOfTypeResolver() {
		TypeResolver typeResolver = new TypeResolver();
		typeResolver.resolve(this.ast);
		return typeResolver.getErrors();
	}

	private int numberOfTypeResolverErrors() {
		return this.errorsOfTypeResolver().size();
	}

	private List<String> errorsOfDuplicatedLabel() {
		DuplicatedLabelChecker labelChecker = new DuplicatedLabelChecker();
		labelChecker.check(this.ast);
		return labelChecker.getWarnings();
	}
}
