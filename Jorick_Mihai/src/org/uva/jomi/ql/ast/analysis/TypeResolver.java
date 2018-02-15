package org.uva.jomi.ql.ast.analysis;

import java.util.List;
import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;

public class TypeResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {
	
	private int numberOfErrors = 0;
	
	public void check(List<Stmt> statements) {		
		for (Stmt statement : statements) {
			statement.accept(this);
		}
	}
	
	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	private void incrementNumberOfErrors() {
		this.numberOfErrors++;
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
		stmt.identifier.accept(this);
		
		return null;
	}
	
	@Override
	public Void visitComputedQuestionStmt(ComputedQuestionStmt stmt) {
		stmt.identifier.accept(this);
		
		// Before traversing the Ast enforce the question type on the expression if needed
		if (stmt.expression.getType() == null) {
			stmt.expression.setType(stmt.getType());
		}
		
		if(stmt.expression.getType() != stmt.getType()) {
			this.incrementNumberOfErrors();
			System.out.println(stmt.label + " expected " + stmt.getType() + " but got " + stmt.expression.getType());
		}
		
		return null;
	}	

	@Override
	public Void visitIfStmt(IfStmt stmt) {
		//TODO Check if if expression is of type boolean.
		stmt.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visitIfElseStmt(IfElseStmt stmt) {
		//TODO Check if if expression is of type boolean.
		stmt.ifBlockStmt.accept(this);
		stmt.elseBlockStmt.accept(this);
		return null;
	}

	@Override
	public Void visitIndetifierExpr(IdentifierExpr expr) {
		return null;
	}

	@Override
	public Void visitPrimaryExpr(PrimaryExpr expr) {
		return null;
	}

	@Override
	public Void visitBinaryExpr(BinaryExpr expr) {
		expr.left.accept(this);
		expr.right.accept(this);
		//TODO Check if left and right expression are equal
		return null;
	}

	@Override
	public Void visitGroupingExpr(GroupingExpr expr) {
		expr.expression.accept(this);
		//TODO Do something?
		return null;
	}

	@Override
	public Void visitUnaryExpr(UnaryExpr expr) {
		expr.right.accept(this);
		//TODO Do something?
		return null;
	}
	
}
