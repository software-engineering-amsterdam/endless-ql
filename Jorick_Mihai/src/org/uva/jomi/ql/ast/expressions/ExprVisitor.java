package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.parser.antlr.*;

public class ExprVisitor extends QLBaseVisitor<Expr> {

	// Builds an Identifier expression using the parser context.
	@Override public Expr visitIdentifierExpr(QLParser.IdentifierExprContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
			return new IdentifierExpr(token);
	}

	// Builds a Boolean expression using the parser context.
	@Override public Expr visitBooleanExpr(QLParser.BooleanExprContext ctx) {
		QLToken token = new QLToken(ctx.BOOLEAN().getSymbol());
		return new PrimaryExpr(token, QLType.BOOLEAN);
	}

	// Builds a String expression using the parser context.
	@Override public Expr visitStringExpr(QLParser.StringExprContext ctx) {
		QLToken token = new QLToken(ctx.LABEL().getSymbol());
		return new PrimaryExpr(token, QLType.STRING);
	}

	// Builds an Integer expression using the parser context.
	@Override public Expr visitIntegerExpr(QLParser.IntegerExprContext ctx) {
		QLToken token = new QLToken(ctx.INTEGER().getSymbol());
		return new PrimaryExpr(token, QLType.INTEGER);
	}

	// Builds an And expression using the parser context.
	@Override public Expr visitAndExpr(QLParser.AndExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);

		switch (ctx.operator.getType()) {
		case QLParser.AND:
			return new AndExpr(left, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in And expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds an Or expression using the parser context.
	@Override public Expr visitOrExpr(QLParser.OrExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);

		switch (ctx.operator.getType()) {
		case QLParser.OR:
			return new OrExpr(left, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Or expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds an Addition expression using the parser context.
	@Override public Expr visitAdditionOrSubtractionExpr(QLParser.AdditionOrSubtractionExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);
		QLToken operator = new QLToken(ctx.operator);

		switch (ctx.operator.getType()) {
		case QLParser.PLUS:
			return new AdditionExpr(left, operator, right);
		case QLParser.MINUS:
			return new SubtractionExpr(left, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in AdditionOrSubtraction expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds an Equality expression using the parser context.
	@Override public Expr visitEqualityExpr(QLParser.EqualityExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);

		switch (ctx.operator.getType()) {
		case QLParser.EQUAL_EQUAL:
			return new EqualExpr(left, right);
		case QLParser.BANG_EQUAL:
			return new NotEqualExpr(left, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Equality expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds a Comparison expression using the parser context.
	@Override public Expr visitComparisonExpr(QLParser.ComparisonExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);

		switch (ctx.operator.getType()) {
		case QLParser.GREATER:
			return new GreaterThanExpr(left, right);
		case QLParser.GREATER_EQUAL:
			return new GreaterThanOrEqualExpr(left, right);
		case QLParser.LESS:
			return new LessThanExpr(left, right);
		case QLParser.LESS_EQUAL:
			return new LessThanOrEqualExpr(left, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Comparison expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds a Multiplication expression using the parser context.
	@Override public Expr visitMultiplicationOrDivisionExpr(QLParser.MultiplicationOrDivisionExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);

		switch (ctx.operator.getType()) {
		case QLParser.STAR:
			return new MultiplicationExpr(left, right);
		case QLParser.SLASH:
			return new DivisionExpr(left, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in MultiplicationOrDivision expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds a Grouping expression using the parser context.
	@Override public Expr visitGroupingExpr(QLParser.GroupingExprContext ctx) {
		Expr expression = ctx.expression().accept(this);
		return new GroupingExpr(expression);
	}

	// Builds an Unary expression using the parser context.
	@Override public Expr visitUnaryExpr(QLParser.UnaryExprContext ctx) {
		Expr right = ctx.expression().accept(this);

		switch (ctx.operator.getType()) {
		case QLParser.BANG:
			return new UnaryNotExpr(right);

		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Unary expression: " + ctx.operator.getText());
			return null;
		}
	}
}