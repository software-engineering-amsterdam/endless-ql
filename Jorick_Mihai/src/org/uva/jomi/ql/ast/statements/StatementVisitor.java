package org.uva.jomi.ql.ast.statements;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.ExpressionVisitor;
import org.uva.jomi.ql.ast.expressions.IdentifierExpression;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.CommandContext;

public class StatementVisitor extends QLBaseVisitor<Statement> {

	private class BlockStatementVisitor extends QLBaseVisitor<BlockStatement> {

		// Builds a Block statement using the parser context.
		@Override public BlockStatement visitBlockStatement(QLParser.BlockStatementContext ctx) {
			List<Statement> statements = new ArrayList<>(ctx.command().size());

			// Visit every statement in the block and add it to the statements array.
			for (CommandContext statement : ctx.command()) {
				statements.add(statement.accept(StatementVisitor.this));
			}

			return new BlockStatement(statements);
		}
	}

	// An expression visitor is needed in order to visit the expression nodes in the Ast.
	private final ExpressionVisitor expressionVisitor;
	private final BlockStatementVisitor blockStatementVisitor;

	// The expression visitor is initialized in the default constructor
	public StatementVisitor(boolean printErrors) {
		this.expressionVisitor = new ExpressionVisitor(printErrors);
		this.blockStatementVisitor = new BlockStatementVisitor();
	}

	public int getNumberOfErrors() {
		return expressionVisitor.getNumberOfErrors();
	}

	// Builds a Form statement using the parser context.
	@Override public Statement visitFormStatement(QLParser.FormStatementContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		IdentifierExpression identifier = new IdentifierExpression(token);
		BlockStatement blockStmt = ctx.blockStatement().accept(blockStatementVisitor);
		return new FormStatement(identifier, blockStmt);
	}

	// Builds a Question statement using the parser context.
	@Override public Statement visitQuestionStatement(QLParser.QuestionStatementContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		String label = ctx.LABEL().getText();
		QLType type = QLType.getType(ctx.TYPE().getText());

		// Set the token and type of the identifier in order for it to match the question type.
		IdentifierExpression identifier = new IdentifierExpression(token, type);

		// Check if the question has an expression
		if (ctx.expression() != null) {
			Expression expression = ctx.expression().accept(expressionVisitor);
			identifier.setUndefined(false);
			return new ComputedQuestionStatement(identifier, label, type, expression);
		}

		return new QuestionStatement(identifier, label, type);
	}

	// Builds an If statement using the parser context.
	@Override public Statement visitIfStatement(QLParser.IfStatementContext ctx) {
		Expression expression = ctx.expression().accept(expressionVisitor);
		BlockStatement blockStmt = ctx.blockStatement().accept(blockStatementVisitor);
		return new IfStatement(expression, blockStmt);
	}

	// Builds an IfElse statement using the parser context.
	@Override public Statement visitIfElseStatement(QLParser.IfElseStatementContext ctx) {
		Expression expression = ctx.expression().accept(expressionVisitor);

		BlockStatement ifBlockStmt = ctx.ifBlock.accept(blockStatementVisitor);
		BlockStatement elseBlockStmt = ctx.elseBlock.accept(blockStatementVisitor);
		return new IfElseStatement(expression, ifBlockStmt, elseBlockStmt);
	}
}