package org.uva.jomi.ql.ast.statements;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.ExprVisitor;
import org.uva.jomi.ql.ast.expressions.IndentifierExpr;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.CommandContext;

public class StmtVisitor extends QLBaseVisitor<Stmt> {
	
	// An expression visitor is needed in order to visit the expression nodes in the Ast.
	private final ExprVisitor exprVisitor;
	
	// The expression visitor is initialized in the default constructor
	public StmtVisitor() {
		this.exprVisitor = new ExprVisitor();
	}
	
	// Builds a Form statement using the parser context.
	@Override public Stmt visitFormStmt(QLParser.FormStmtContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		IndentifierExpr identifier = new IndentifierExpr(token);
		BlockStmt blockStmt = (BlockStmt) visitBlockStmt(ctx.blockStmt());
		return new FormStmt(identifier, blockStmt);
	}
	
	// Builds a Block statement using the parser context.
	@Override public Stmt visitBlockStmt(QLParser.BlockStmtContext ctx) {
		List<Stmt> statements = new ArrayList<>(ctx.command().size());
		
		// Visit every statement in the block and add it to the statements array.
		for (CommandContext statement : ctx.command()) {
			statements.add(visit(statement));
		}
		
		return new BlockStmt(statements);
	}
	
	// Builds a Question statement using the parser context.
	@Override public Stmt visitQuestionStmt(QLParser.QuestionStmtContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		String label = ctx.LABEL().getText();
		QLType type = QLType.getType(ctx.TYPE().getText());
		
		// Set the token and type of the identifier in order for it to match the question type.
		IndentifierExpr identifier = new IndentifierExpr(token, type);
		
		// Check if the question has an expression
		if (ctx.expression() != null) {
			Expr expression = ctx.expression().accept(exprVisitor);
			return new QuestionStmt(identifier, label, type, expression);
		}
		
		return new QuestionStmt(identifier, label, type);
	}
	
	// Builds an If statement using the parser context.
	@Override public Stmt visitIfStmt(QLParser.IfStmtContext ctx) {
		Expr expression = ctx.expression().accept(exprVisitor);
		BlockStmt blockStmt = (BlockStmt) visitBlockStmt(ctx.blockStmt());
		return new IfStmt(expression, blockStmt);
	}
}