package org.uva.jomi.ql.ast;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.AdditionExpr;
import org.uva.jomi.ql.ast.expressions.BinaryExpr;
import org.uva.jomi.ql.ast.expressions.DivisionExpr;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.GroupingExpr;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;
import org.uva.jomi.ql.ast.expressions.MultiplicationExpr;
import org.uva.jomi.ql.ast.expressions.PrimaryExpr;
import org.uva.jomi.ql.ast.expressions.SubtractionExpr;
import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.ast.statements.UnaryExpr;

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
	public String visit(IdentifierExpr expr) {
		return String.format("  %s [label=\"[Ident]\nName: %s\nType: %s\nUndefined: %s\"]\n",
				expr.getId(),
				expr.token.getLexeme(),
				expr.getType(),
				expr.isUndefined());
	}

	@Override
	public String visit(PrimaryExpr expr) {
		String value = null;
		
		switch (expr.getType()) {
		case BOOLEAN:
			value = String.format("Type: %s\nValue: %s\n", expr.getType(), expr.token.getLexeme());
			break;
		case STRING:
			// Remove double quotes at from the start and end of the string
			value = expr.token.getLexeme().substring(1, expr.token.getLexeme().length() - 1);
			value = String.format("Type: %s\nValue: %s\n", expr.getType(), value);
			break;
		case INTEGER:
			value = String.format("Type: %s\nValue: %s\n", expr.getType(), expr.token.getLexeme());
			break;
		default:
			// TODO Improve error by displaying the location of the offending token.
			System.err.println("[AstGraph] Unexpected literal expression: " + expr.token.getLexeme().toString());
			break;
		}
		
		return String.format("  %s [label=\"%s\"]\n", expr.getId(), value);
	}

	@Override
	public String visit(FormStmt stmt) {
		return stmt.blockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.blockStmt.getId()) +
				stmt.identifier.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", stmt.getId(), stmt.identifier.token.getLexeme());
	}

	@Override
	public String visit(BlockStmt stmt) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", stmt.getId());
		
		for (Stmt statement : stmt.statements) {
			header += String.format("  %s -> %s\n", stmt.getId(), statement.getId());
			String result = statement.accept(this);
			header += result;
		}
		
		return header;
	}

	@Override
	public String visit(QuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
			   stmt.getId(),
			   stmt.identifier.token.getLexeme(),
			   stmt.type.getName());
		// Visit the identifier expression
		header += stmt.identifier.accept(this);
		header += String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId());
		
		return header;
	}
	
	@Override
	public String visit(ComputedQuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
				   stmt.getId(),
				   stmt.identifier.token.getLexeme(),
				   stmt.type.getName());
			// Visit the expression statement
			header += stmt.expression.accept(this);
			header += String.format("  %s -> %s\n", stmt.getId(), stmt.expression.getId());
			
			// Visit the identifier expression
			header += stmt.identifier.accept(this);
			header += String.format("  %s -> %s\n", stmt.getId(), stmt.identifier.getId());
			
			return header;
	}

	@Override
	public String visit(BinaryExpr expr) {
		return expr.left.accept(this) +
				expr.right.accept(this) +
				String.format("  %s [label=\"%s\nType: %s\n\"]\n", expr.getId(), expr.operator.getLexeme(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getId(), expr.left.getId()) +
				String.format("  %s -> %s\n", expr.getId(), expr.right.getId());
	}

	@Override
	public String visit(GroupingExpr expr) {
		return expr.expression.accept(this) +
				String.format("  %s [label=\"GroupingExpr\nType: %s\"]\n", expr.getId(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getId(), expr.expression.getId());
	}

	@Override
	public String visit(UnaryExpr expr) {
		return expr.right.accept(this) + String.format("  %s [label=\"%s\nType: %s\n\"]\n", expr.getId(), expr.operator.getLexeme(), expr.getType()) +
		 		String.format("  %s -> %s\n", expr.getId(), expr.right.getId());
	}

	@Override
	public String visit(IfStmt stmt) {
		return stmt.expression.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.expression.getId()) +
				stmt.blockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.blockStmt.getId()) +
				String.format("  %s [label=\"IfStmt\"]\n", stmt.getId());
	}

	@Override
	public String visit(IfElseStmt stmt) {
		return stmt.expression.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.expression.getId()) +
				stmt.ifBlockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.ifBlockStmt.getId()) +
				stmt.elseBlockStmt.accept(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.elseBlockStmt.getId()) +
				String.format("  %s [label=\"IfElseStmt\"]\n", stmt.getId());
	}

	@Override
	public String visit(AdditionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SubtractionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(MultiplicationExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DivisionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}
}