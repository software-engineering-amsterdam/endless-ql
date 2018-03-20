package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.error.ErrorHandler;
import org.uva.jomi.ql.parser.antlr.*;
import org.uva.jomi.ql.parser.antlr.QLParser.ExpressionContext;

public class ExpressionVisitor extends QLBaseVisitor<Expression> {

	private ErrorHandler errorHandler;

	public ExpressionVisitor(boolean printErrors) {
		this.errorHandler = new ErrorHandler(this.getClass().getSimpleName(), printErrors);
	}

	public int getNumberOfErrors() {
		return errorHandler.getNumberOfErrors();
	}

	// Sets the line and column number of an expression based on a ANTLR expression context.
	public void setPosition(Expression expression, ExpressionContext context) {
		int line = context.getStart().getLine();
		int column = context.getStart().getCharPositionInLine();
		expression.setLineNumber(line);
		expression.setColumnNumber(column);
	}

	// Builds an Identifier expression using the parser context.
	@Override public Expression visitIdentifierExpression(QLParser.IdentifierExpressionContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
			return new IdentifierExpression(token);
	}

	// Builds a Boolean expression using the parser context.
	@Override public Expression visitBooleanExpression(QLParser.BooleanExpressionContext ctx) {
		QLToken token = new QLToken(ctx.BOOLEAN().getSymbol());
		return new BooleanExpression(token, QLType.BOOLEAN);
	}

	// Builds a String expression using the parser context.
	@Override public Expression visitStringExpression(QLParser.StringExpressionContext ctx) {
		QLToken token = new QLToken(ctx.LABEL().getSymbol());
		return new StringExpression(token, QLType.STRING);
	}

	// Builds an Integer expression using the parser context.
	@Override public Expression visitIntegerExpression(QLParser.IntegerExpressionContext ctx) {
		QLToken token = new QLToken(ctx.INTEGER().getSymbol());
		return new IntegerExpression(token, QLType.INTEGER);
	}

	// Builds an And expression using the parser context.
	@Override public Expression visitAndExpression(QLParser.AndExpressionContext ctx) {
		Expression left = ctx.left.accept(this);
		Expression right = ctx.right.accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(left, ctx.left);
		setPosition(right, ctx.right);

		switch (ctx.operator.getType()) {
		case QLParser.AND:
			return new AndExpression(left, operator, right);
		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in And expression: ");
			return null;
		}
	}

	// Builds an Or expression using the parser context.
	@Override public Expression visitOrExpression(QLParser.OrExpressionContext ctx) {
		Expression left = ctx.left.accept(this);
		Expression right = ctx.right.accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(left, ctx.left);
		setPosition(right, ctx.right);

		switch (ctx.operator.getType()) {
		case QLParser.OR:
			return new OrExpression(left, operator, right);
		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in Or expression: ");
			return null;
		}
	}

	// Builds an Addition expression using the parser context.
	@Override public Expression visitAdditionOrSubtractionExpression(QLParser.AdditionOrSubtractionExpressionContext ctx) {
		Expression left = ctx.left.accept(this);
		Expression right = ctx.right.accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(left, ctx.left);
		setPosition(right, ctx.right);

		switch (ctx.operator.getType()) {
		case QLParser.PLUS:
			return new AdditionExpression(left, operator, right);
		case QLParser.MINUS:
			return new SubtractionExpression(left, operator, right);
		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in AdditionOrSubtraction expression: ");
			return null;
		}
	}

	// Builds an Equality expression using the parser context.
	@Override public Expression visitEqualityExpression(QLParser.EqualityExpressionContext ctx) {
		Expression left = ctx.left.accept(this);
		Expression right = ctx.right.accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(left, ctx.left);
		setPosition(right, ctx.right);

		switch (ctx.operator.getType()) {
		case QLParser.EQUAL_EQUAL:
			return new EqualExpression(left, operator, right);
		case QLParser.BANG_EQUAL:
			return new NotEqualExpression(left, operator, right);
		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in Equality expression: ");
			return null;
		}
	}

	// Builds a Comparison expression using the parser context.
	@Override public Expression visitComparisonExpression(QLParser.ComparisonExpressionContext ctx) {
		Expression left = ctx.left.accept(this);
		Expression right = ctx.right.accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(left, ctx.left);
		setPosition(right, ctx.right);

		switch (ctx.operator.getType()) {
		case QLParser.GREATER:
			return new GreaterThanExpression(left, operator, right);
		case QLParser.GREATER_EQUAL:
			return new GreaterThanOrEqualExpression(left, operator, right);
		case QLParser.LESS:
			return new LessThanExpression(left, operator, right);
		case QLParser.LESS_EQUAL:
			return new LessThanOrEqualExpression(left, operator, right);
		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in Comparison expression: ");
			return null;
		}
	}

	// Builds a Multiplication expression using the parser context.
	@Override public Expression visitMultiplicationOrDivisionExpression(QLParser.MultiplicationOrDivisionExpressionContext ctx) {
		Expression left = ctx.left.accept(this);
		Expression right = ctx.right.accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(left, ctx.left);
		setPosition(right, ctx.right);

		switch (ctx.operator.getType()) {
		case QLParser.STAR:
			return new MultiplicationExpression(left, operator, right);
		case QLParser.SLASH:
			return new DivisionExpression(left, operator, right);
		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in MultiplicationOrDivision expression: ");
			return null;
		}
	}

	// Builds a Grouping expression using the parser context.
	@Override public Expression visitGroupingExpression(QLParser.GroupingExpressionContext ctx) {
		Expression expression = ctx.expression().accept(this);

		setPosition(expression, ctx.expression());

		return new GroupingExpression(expression);
	}

	// Builds an Unary expression using the parser context.
	@Override public Expression visitUnaryExpression(QLParser.UnaryExpressionContext ctx) {
		Expression right = ctx.expression().accept(this);
		QLToken operator = new QLToken(ctx.operator);

		setPosition(right, ctx.expression());

		switch (ctx.operator.getType()) {
		case QLParser.BANG:
			return new UnaryNotExpression(operator, right);

		default:
			errorHandler.addIdentifierError(new QLToken(ctx.operator), "Illegal oprator found in Unary expression: ");
			return null;
		}
	}
}