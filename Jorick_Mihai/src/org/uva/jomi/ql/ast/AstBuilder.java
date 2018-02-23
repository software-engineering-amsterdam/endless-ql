package org.uva.jomi.ql.ast;

import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.ast.statements.StmtVisitor;
import org.uva.jomi.ql.parser.antlr.*;
import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends QLBaseVisitor<List<Stmt>> {
	private final StmtVisitor stmtVisitor;

	public AstBuilder() {
		stmtVisitor = new StmtVisitor();
	}

	@Override public List<Stmt> visitParse(QLParser.ParseContext ctx) {
		List<Stmt> statements = new ArrayList<>(ctx.formStmt().size());
		for (QLParser.FormStmtContext statement : ctx.formStmt()) {
			statements.add(statement.accept(stmtVisitor));
		}

		return statements;
	}
}

