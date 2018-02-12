package org.uva.jomi.ql.ast;

import java.util.List;

public class AstGraph implements Stmt.Visitor<String>, Expr.Visitor<String> {

	public String getGraph(List<Stmt> statements) {
		String header = "digraph G {\n" + "  node [shape=\"box\"]\n";
		
		for (Stmt statement : statements) {
			String result = statement.accept(this);
			header += result;
		}

		return header + "}";
	}

	@Override
	public String visitIndetifierExpr(IndentifierExpr expr) {
		return String.format("  %s [label=\"[Ident]\nName: %s\nType: %s\nUndefined: %s\"]\n",
				expr.getId(),
				expr.token.getLexeme(),
				expr.getType(),
				expr.isUndefined());
	}

	@Override
	public String visitPrimaryExpr(PrimaryExpr expr) {
		String value = null;
		
		switch (expr.getType()) {
		case BOOLEAN:
			value = String.format("[%s] %s", expr.getType(), expr.token.toString());
			break;
		case STRING:
			// Remove double quotes at from the start and end of the string
			value = expr.token.getLexeme().substring(1, expr.token.getLexeme().length() - 1);
			value = String.format("[%s] %s", expr.getType(), value);
			break;
		case INTEGER:
			value = String.format("[%s] %s", expr.getType(), expr.token.getLexeme());
			break;
		default:
			// TODO Improve error by displaying the location of the offending token.
			System.err.println("[AstGraph] Unexpected literal expression: " + expr.token.getLexeme().toString());
			break;
		}
		
		return String.format("  %s [label=\"%s\"]\n", expr.getId(), value);
	}

	@Override
	public String visitFormStmt(FormStmt stmt) {
		return stmt.blockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.blockStmt.getId()) +
				stmt.identifier.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", stmt.getId(), stmt.identifier.token.getLexeme());
	}

	@Override
	public String visitBlockStmt(BlockStmt stmt) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", stmt.getId());
		
		for (Stmt statement : stmt.statements) {
			header += String.format("  %s -> %s\n", stmt.getId(), statement.getId());
			String result = statement.accept(this);
			header += result;
		}
		
		return header;
	}

	@Override
	public String visitQuestionStmt(QuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
			   stmt.getId(),
			   stmt.identifier.token.getLexeme(),
			   stmt.type.getName());
		// Visit the optional expression statement
		if (stmt.expression != null) {
			header += stmt.expression.accept(this);
			header += String.format("  %s -> %s\n", stmt.getId(), stmt.expression.getId());
		}
		// Visit the identifier expression
		header += stmt.identifier.accept(this);
		header += String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId());
		
		return header;
	}

	@Override
	public String visitBinaryExpr(BinaryExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitGroupingExpr(GroupingExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitUnaryExpr(UnaryExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

}
