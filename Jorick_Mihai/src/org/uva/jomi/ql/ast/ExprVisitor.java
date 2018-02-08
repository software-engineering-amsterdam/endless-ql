package org.uva.jomi.ql.ast;

import org.uva.jomi.ql.parser.antlr.*;

class ExprVisitor extends QLBaseVisitor<Expr> {
	@Override public Expr visitIdentifierExpr(QLParser.IdentifierExprContext ctx) {
		QLToken token = new QLToken(ctx.IDENTIFIER().getSymbol());
		return new IndentifierExpr(token);
	}
}
