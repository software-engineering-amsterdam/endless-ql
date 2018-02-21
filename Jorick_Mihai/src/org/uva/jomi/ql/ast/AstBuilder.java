package org.uva.jomi.ql.ast;

import org.uva.jomi.ql.ast.analysis.IdentifierResolver;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.ast.statements.StmtVisitor;
import org.uva.jomi.ql.parser.antlr.*;
import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends QLBaseVisitor<List<Stmt>> {
	private final StmtVisitor stmtVisitor;
	private final IdentifierResolver identifierResolver;

	public AstBuilder() {
		identifierResolver = new IdentifierResolver();
		stmtVisitor = new StmtVisitor(identifierResolver);
	}

	@Override public List<Stmt> visitParse(QLParser.ParseContext ctx) {
		List<Stmt> statements = new ArrayList<>(ctx.formStmt().size());
		for (QLParser.FormStmtContext statement : ctx.formStmt()) {
			statements.add(statement.accept(stmtVisitor));
		}

		return statements;
	}

	// Get the number of errors that occurred during the Ast build process.
	public int getNumberOfBuildErrors() {
		// Returns the number of errors captured by the identifier resolver.
		return identifierResolver.getNumberOfErrors();
	}
}

