package org.uva.jomi.ql.ast.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import java.util.HashMap;
import java.util.Stack;

public class TypeResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {
	
	private int numberOfErrors = 0;
	private final HashMap<String, QLType> identifierStack;

	public TypeResolver() {
		this.identifierStack = new HashMap<String, QLType>();
	}
	
	public void check(List<Stmt> statements) {
		this.numberOfErrors += 1;
		
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
		
		// Adding Identifier/Variable to the stack
		this.identifierStack.put(stmt.identifier.token.getLexeme(), stmt.getType());
		
		return null;
	}
	
	@Override
	public Void visitComputedQuestionStmt(ComputedQuestionStmt stmt) {
		stmt.identifier.accept(this);
		
		// Adding Identifier/Variable to the stack
		this.identifierStack.put(stmt.identifier.token.getLexeme(), stmt.getType());
		
		
		if(stmt.expression instanceof IndentifierExpr) {
			IndentifierExpr expr = (IndentifierExpr) stmt.expression;
			QLType type = this.identifierStack.get(expr.token.getLexeme());
			if(type != null) {
				if(stmt.getType() != type) {
					this.incrementNumberOfErrors();
					System.out.println("Identifier: " + expr.token.getLexeme() + " type mismatch. Expected " + stmt.getType() + " got " + type);
				}
			} else {
				this.incrementNumberOfErrors();
				System.out.println("Identifier not defined");
			}
		}
		
		// Before traversing the Ast enforce the question type on the expression if needed
		if (stmt.expression.getType() == null) {
			stmt.expression.setType(stmt.getType());
		}
		
		if(stmt.expression.getType() != stmt.getType()) {
			this.incrementNumberOfErrors();
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
