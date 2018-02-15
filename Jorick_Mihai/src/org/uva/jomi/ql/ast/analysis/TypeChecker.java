package org.uva.jomi.ql.ast.analysis;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;

public class TypeChecker implements Expr.Visitor<Void>, Stmt.Visitor<Void> {

	public void check(List<Stmt> statements) {
		for (Stmt statement : statements) {
			statement.accept(this);
		}
	}
	
	@Override
	public Void visitFormStmt(FormStmt form) {
		form.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visitBlockStmt(BlockStmt block) {
		for (Stmt statement : block.statements) {
			statement.accept(this);
		}
		return null;
	}

	@Override
	public Void visitQuestionStmt(QuestionStmt stmt) {
		stmt.accept(this);
		//TODO add check if question statement is equal to they question type
		return null;
	}
	
	@Override
	public Void visitComputedQuestionStmt(ComputedQuestionStmt stmt) {
		// Before traversing the Ast enforce the question type on the expression if needed
		if (stmt.expression.getType() == null) {
			stmt.expression.setType(stmt.getType());
		}
		return null;
	}	

	@Override
	public Void visitIfStmt(IfStmt stmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitIfElseStmt(IfElseStmt stmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitIndetifierExpr(IndentifierExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitPrimaryExpr(PrimaryExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitBinaryExpr(BinaryExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitGroupingExpr(GroupingExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitUnaryExpr(UnaryExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
