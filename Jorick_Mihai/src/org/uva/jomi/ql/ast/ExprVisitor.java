package org.uva.jomi.ql.ast;

import org.uva.jomi.ql.parser.antlr.*;

class ExprVisitor extends QLBaseVisitor<Expr> {
	
	// Builds an Identifier expression using the parser context. 
	@Override public Expr visitIdentifierExpr(QLParser.IdentifierExprContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		return new IndentifierExpr(token);
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
		QLToken operator = null;
		
		switch (ctx.operator.getType()) {
		case QLParser.AND:
			operator = new QLToken(QLTokenType.AND, ctx.operator);
			break;
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in And expression: " + ctx.operator.getText());
			break;
		}
		
		return new BinaryExpr(left, operator, right);
	}
	
	// Builds an Or expression using the parser context.
	@Override public Expr visitOrExpr(QLParser.OrExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);
		QLToken operator = null;
		
		switch (ctx.operator.getType()) {
		case QLParser.OR:
			operator = new QLToken(QLTokenType.OR, ctx.operator);
			break;
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Or expression: " + ctx.operator.getText());
			break;
		}
		
		return new BinaryExpr(left, operator, right);
	}
	
	// Builds an Equality expression using the parser context.
	@Override public Expr visitEqualityExpr(QLParser.EqualityExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);
		QLToken operator = null;
		
		switch (ctx.operator.getType()) {
		case QLParser.EQUAL_EQUAL:
			operator = new QLToken(QLTokenType.EQUAL_EQUAL, ctx.operator);
			break;
		case QLParser.BANG_EQUAL:
			operator = new QLToken(QLTokenType.BANG_EQUAL, ctx.operator);
			break;
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Equality expression: " + ctx.operator.getText());
			break;
		}
		
		return new BinaryExpr(left, operator, right);
	}
	
	// Builds a Comparison expression using the parser context.
	@Override public Expr visitComparisonExpr(QLParser.ComparisonExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);
		QLToken operator = null;
		
		switch (ctx.operator.getType()) {
		case QLParser.GREATER:
			operator = new QLToken(QLTokenType.GREATER, ctx.operator);
			break;
		case QLParser.GREATER_EQUAL:
			operator = new QLToken(QLTokenType.GREATER_EQUAL, ctx.operator);
			break;
		case QLParser.LESS:
			operator = new QLToken(QLTokenType.LESS, ctx.operator);
			break;
		case QLParser.LESS_EQUAL:
			operator = new QLToken(QLTokenType.LESS_EQUAL, ctx.operator);
			break;
		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Comparison expression: " + ctx.operator.getText());
			break;
		}
		
		return new BinaryExpr(left, operator, right);
	}
	
	// Builds a Multiplication expression using the parser context.
	@Override public Expr visitMultiplicationExpr(QLParser.MultiplicationExprContext ctx) {
		Expr left = ctx.expression(0).accept(this);
		Expr right = ctx.expression(1).accept(this);
		QLToken operator = null;
		switch (ctx.operator.getType()) {
		case QLParser.STAR:
			operator = new QLToken(QLTokenType.STAR, ctx.operator);
			break;
		case QLParser.SLASH:
			operator = new QLToken(QLTokenType.SLASH, ctx.operator);

		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Multiplication expression: " + ctx.operator.getText());
			break;
		}
		
		return new BinaryExpr(left, operator, right);
	}
	
	// Builds a Grouping expression using the parser context.
	@Override public Expr visitGroupingExpr(QLParser.GroupingExprContext ctx) {
		Expr expression = ctx.expression().accept(this);
		return new GroupingExpr(expression);
	}
	
	// Builds an Unary expression using the parser context.
	@Override public Expr visitUnaryExpr(QLParser.UnaryExprContext ctx) {
		Expr right = ctx.expression().accept(this);
		QLToken operator = null;
		
		switch (ctx.operator.getType()) {
		case QLParser.BANG:
			operator = new QLToken(QLTokenType.BANG, ctx.operator);
			break;

		default:
			// TODO - Needs to go into an Error class.
			System.err.println("Illegal oprator found in Unary expression: " + ctx.operator.getText());
			break;
		}
		
		return new UnaryExpr(operator, right);
	}
}