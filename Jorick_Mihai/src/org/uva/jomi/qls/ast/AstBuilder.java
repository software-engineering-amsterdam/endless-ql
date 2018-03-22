package org.uva.jomi.qls.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.qls.ast.statements.Stmt;
import org.uva.jomi.qls.ast.statements.StmtVisitor;
import org.uva.jomi.qls.parser.antlr.QLSParser;
import org.uva.jomi.qls.parser.antlr.QLSBaseVisitor;

public class AstBuilder extends QLSBaseVisitor<List<Stmt>> {
	private final StmtVisitor stmtVisitor;

	public AstBuilder() {
		stmtVisitor = new StmtVisitor();
	}

	@Override
	public List<Stmt> visitParse(QLSParser.ParseContext ctx) {
		List<Stmt> statements = new ArrayList<>(ctx.stylesheetStmt().size());
		for (QLSParser.StylesheetStmtContext statement : ctx.stylesheetStmt()) {
			statements.add(statement.accept(stmtVisitor));
		}

		return statements;
	}
}
