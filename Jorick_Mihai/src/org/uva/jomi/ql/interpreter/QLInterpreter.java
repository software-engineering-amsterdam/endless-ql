package org.uva.jomi.ql.interpreter;

import java.util.List;

import org.uva.jomi.ql.ast.*;
import org.uva.jomi.ql.ast.expressions.BinaryExpr;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.GroupingExpr;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;
import org.uva.jomi.ql.ast.expressions.PrimaryExpr;
import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.ast.statements.UnaryExpr;


public class QLInterpreter implements Stmt.Visitor<Void>, Expr.Visitor<Object> {
	
	public void interpret(List<Stmt> statements) {
		for (Stmt statement : statements) {
			execute(statement);
		}
	}
	
	private Object evaluate(Expr expr) {
		return expr.accept(this);
	}
	
	private void execute(Stmt stmt) {
		stmt.accept(this);
	}

	@Override
	public Object visitIndetifierExpr(IdentifierExpr expr) {
		// TODO Interpret IndentifierExpr.
		return null;
	}

	@Override
	public Object visitPrimaryExpr(PrimaryExpr expr) {
		// TODO Interpret PrimaryExpr.
		return null;
	}

	@Override
	public Object visitBinaryExpr(BinaryExpr expr) {
		// TODO Interpret BinaryExpr.
		return null;
	}

	@Override
	public Object visitGroupingExpr(GroupingExpr expr) {
		// TODO Interpret GroupingExpr.
		return null;
	}

	@Override
	public Object visitUnaryExpr(UnaryExpr expr) {
		// TODO Interpret UnaryExpr.
		return null;
	}

	@Override
	public Void visitFormStmt(FormStmt stmt) {
		// TODO Interpret FormStmt.
		return null;
	}

	@Override
	public Void visitBlockStmt(BlockStmt stmt) {
		// TODO Interpret BlockStmt.
		return null;
	}

	@Override
	public Void visitQuestionStmt(QuestionStmt stmt) {
		// TODO Interpret QuestionStmt.
		return null;
	}
	
	@Override
	public Void visitComputedQuestionStmt(ComputedQuestionStmt stmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitIfStmt(IfStmt stmt) {
		// TODO Interpret IfStmt.
		return null;
	}

	@Override
	public Void visitIfElseStmt(IfElseStmt stmt) {
		// TODO Auto-generated method stub
		return null;
	}

}
