package org.uva.jomi.ql.ast.statements;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.ExprVisitor;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.CommandContext;

public class StmtVisitor extends QLBaseVisitor<Stmt> {

	private class BlockStmtVisitor extends QLBaseVisitor<BlockStmt> {

		// Builds a Block statement using the parser context.
		@Override public BlockStmt visitBlockStmt(QLParser.BlockStmtContext ctx) {
			List<Stmt> statements = new ArrayList<>(ctx.command().size());

			// Visit every statement in the block and add it to the statements array.
			for (CommandContext statement : ctx.command()) {
				statements.add(statement.accept(StmtVisitor.this));
			}

			return new BlockStmt(statements);
		}
	}

	// An expression visitor is needed in order to visit the expression nodes in the Ast.
	private final ExprVisitor exprVisitor;
	private final BlockStmtVisitor blockStmtVisitor;

	// The expression visitor is initialized in the default constructor
	public StmtVisitor(boolean printErrors) {
		this.exprVisitor = new ExprVisitor(printErrors);
		this.blockStmtVisitor = new BlockStmtVisitor();
	}

	public int getNumberOfErrors() {
		return exprVisitor.getNumberOfErrors();
	}

	// Builds a Form statement using the parser context.
	@Override public Stmt visitFormStmt(QLParser.FormStmtContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		IdentifierExpr identifier = new IdentifierExpr(token);
		BlockStmt blockStmt = ctx.blockStmt().accept(blockStmtVisitor);
		return new FormStmt(identifier, blockStmt);
	}

	// Builds a Question statement using the parser context.
	@Override public Stmt visitQuestionStmt(QLParser.QuestionStmtContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		String label = ctx.LABEL().getText();
		QLType type = QLType.getType(ctx.TYPE().getText());

		// Set the token and type of the identifier in order for it to match the question type.
		IdentifierExpr identifier = new IdentifierExpr(token, type);

		// Check if the question has an expression
		if (ctx.expression() != null) {
			Expr expression = ctx.expression().accept(exprVisitor);
			identifier.setUndefined(false);
			return new ComputedQuestionStmt(identifier, label, type, expression);
		}

		return new QuestionStmt(identifier, label, type);
	}

	// Builds an If statement using the parser context.
	@Override public Stmt visitIfStmt(QLParser.IfStmtContext ctx) {
		Expr expression = ctx.expression().accept(exprVisitor);
		BlockStmt blockStmt = ctx.blockStmt().accept(blockStmtVisitor);
		return new IfStmt(expression, blockStmt);
	}

	// Builds an IfElse statement using the parser context.
	@Override public Stmt visitIfElseStmt(QLParser.IfElseStmtContext ctx) {
		Expr expression = ctx.expression().accept(exprVisitor);

		BlockStmt ifBlockStmt = ctx.ifBlock.accept(blockStmtVisitor);
		BlockStmt elseBlockStmt = ctx.elseBlock.accept(blockStmtVisitor);
		return new IfElseStmt(expression, ifBlockStmt, elseBlockStmt);
	}
}