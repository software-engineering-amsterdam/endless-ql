package org.uva.jomi.ql.ast;

import org.uva.jomi.ql.parser.antlr.*;

class ExprVisitor extends QLBaseVisitor<Expr> {
	@Override public Expr visitIdentifierExpr(QLParser.IdentifierExprContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		return new IndentifierExpr(token);
	}
	
	@Override public Expr visitBooleanExpr(QLParser.BooleanExprContext ctx) {
		QLToken token = new QLToken(ctx.BOOLEAN().getSymbol());
		return new PrimaryExpr(token, QLType.BOOLEAN);
	}
	
	@Override public Expr visitStringExpr(QLParser.StringExprContext ctx) {
		QLToken token = new QLToken(ctx.LABEL().getSymbol());
		return new PrimaryExpr(token, QLType.STRING);
	}
	
	@Override public Expr visitIntegerExpr(QLParser.IntegerExprContext ctx) {
		QLToken token = new QLToken(ctx.INTEGER().getSymbol());
		return new PrimaryExpr(token, QLType.INTEGER);
	}
}