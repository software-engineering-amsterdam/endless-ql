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
		QLToken operator = new QLToken(ctx.operator);

		left.setLineNumber(ctx.expression(0).getStart().getLine());
		left.setColumnNumber(ctx.expression(0).getStart().getCharPositionInLine());
		right.setLineNumber(ctx.expression(1).getStart().getLine());
		right.setColumnNumber(ctx.expression(1).getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.AND:
			return new AndExpr(left, operator, right);
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
		QLToken operator = new QLToken(ctx.operator);
		
		left.setLineNumber(ctx.expression(0).getStart().getLine());
		left.setColumnNumber(ctx.expression(0).getStart().getCharPositionInLine());
		right.setLineNumber(ctx.expression(1).getStart().getLine());
		right.setColumnNumber(ctx.expression(1).getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.OR:
			return new OrExpr(left, operator, right);
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
		
		left.setLineNumber(ctx.expression(0).getStart().getLine());
		left.setColumnNumber(ctx.expression(0).getStart().getCharPositionInLine());
		right.setLineNumber(ctx.expression(1).getStart().getLine());
		right.setColumnNumber(ctx.expression(1).getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.PLUS:
			return new AdditionExpr(left, operator, right);
		case QLParser.MINUS:
			return new SubtractionExpr(left, operator, right);
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
		QLToken operator = new QLToken(ctx.operator);
		
		left.setLineNumber(ctx.expression(0).getStart().getLine());
		left.setColumnNumber(ctx.expression(0).getStart().getCharPositionInLine());
		right.setLineNumber(ctx.expression(1).getStart().getLine());
		right.setColumnNumber(ctx.expression(1).getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.EQUAL_EQUAL:
			return new EqualExpr(left, operator, right);
		case QLParser.BANG_EQUAL:
			return new NotEqualExpr(left, operator, right);
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
		QLToken operator = new QLToken(ctx.operator);
		
		left.setLineNumber(ctx.expression(0).getStart().getLine());
		left.setColumnNumber(ctx.expression(0).getStart().getCharPositionInLine());
		right.setLineNumber(ctx.expression(1).getStart().getLine());
		right.setColumnNumber(ctx.expression(1).getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.GREATER:
			return new GreaterThanExpr(left, operator, right);
		case QLParser.GREATER_EQUAL:
			return new GreaterThanOrEqualExpr(left, operator, right);
		case QLParser.LESS:
			return new LessThanExpr(left, operator, right);
		case QLParser.LESS_EQUAL:
			return new LessThanOrEqualExpr(left, operator, right);
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
		QLToken operator = new QLToken(ctx.operator);
		
		left.setLineNumber(ctx.expression(0).getStart().getLine());
		left.setColumnNumber(ctx.expression(0).getStart().getCharPositionInLine());
		right.setLineNumber(ctx.expression(1).getStart().getLine());
		right.setColumnNumber(ctx.expression(1).getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.STAR:
			return new MultiplicationExpr(left, operator, right);
		case QLParser.SLASH:
			return new DivisionExpr(left, operator, right);
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in MultiplicationOrDivision expression: " + ctx.operator.getText());
			return null;
		}
	}

	// Builds a Grouping expression using the parser context.
	@Override public Expr visitGroupingExpr(QLParser.GroupingExprContext ctx) {
		Expr expression = ctx.expression().accept(this);
		
		expression.setLineNumber(ctx.expression().getStart().getLine());
		expression.setColumnNumber(ctx.expression().getStart().getCharPositionInLine());
		
		return new GroupingExpr(expression);
	}

	// Builds an Unary expression using the parser context.
	@Override public Expr visitUnaryExpr(QLParser.UnaryExprContext ctx) {
		Expr right = ctx.expression().accept(this);
		QLToken operator = new QLToken(ctx.operator);
		
		right.setLineNumber(ctx.expression().getStart().getLine());
		right.setColumnNumber(ctx.expression().getStart().getCharPositionInLine());

		switch (ctx.operator.getType()) {
		case QLParser.BANG:
			return new UnaryNotExpr(operator, right);

		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Unary expression: " + ctx.operator.getText());
			return null;
		}
	}
}