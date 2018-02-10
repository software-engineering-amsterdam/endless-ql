package org.uva.jomi.ql.ast;

import java.util.ArrayList;
import java.util.List;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.CommandContext;

public class StmtVisitor extends QLBaseVisitor<Stmt> {
	
	private final ExprVisitor exprVisitor;
	
	public StmtVisitor() {
		this.exprVisitor = new ExprVisitor();
	}

	@Override public Stmt visitFormStmt(QLParser.FormStmtContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		IndentifierExpr identifier = new IndentifierExpr(token);
		BlockStmt blockStmt = (BlockStmt) visitBlockStmt(ctx.blockStmt());
		return new FormStmt(identifier, blockStmt);
	}
	
	@Override public Stmt visitBlockStmt(QLParser.BlockStmtContext ctx) {
		List<Stmt> statements = new ArrayList<>(ctx.command().size());
		
		for (CommandContext statement : ctx.command()) {
			statements.add(visit(statement));
		}
		
		return new BlockStmt(statements);
	}
	
	@Override public Stmt visitQuestionStmt(QLParser.QuestionStmtContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		String label = ctx.LABEL().getText();
		QLType type = QLType.getType(ctx.TYPE().getText());
		// Set the token and type of the identifier in order for it to match the question type
		IndentifierExpr identifier = new IndentifierExpr(token, type);
		// Check if the question has an expression
		if (ctx.expression() != null) {
			Expr expression = ctx.expression().accept(exprVisitor);
			return new QuestionStmt(identifier, label, type, expression);
		}
		
		return new QuestionStmt(identifier, label, type);
	}
}