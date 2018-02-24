package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;

public class TypeResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {
	
	private List<String> errorMessages;
	
	public TypeResolver() {
		this.errorMessages = new ArrayList<String>();
	}
	
	public void check(List<Stmt> statements) {		
		for (Stmt statement : statements) {
			statement.accept(this);
		}
	}
	
	public int getNumberOfErrors() {
		return this.errorMessages.size();
	}
	
	public void addError(String error) {
		this.errorMessages.add(error);
		System.out.println(error);
	}
	
	
	@Override
	public Void visit(FormStmt form) {
		form.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(BlockStmt block) {
		for (Stmt statement : block.statements) {
			statement.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(QuestionStmt stmt) {
		stmt.identifier.accept(this);
		
		return null;
	}
	
	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		stmt.identifier.accept(this);
		stmt.expression.accept(this);
		
		// Before traversing the Ast enforce the question type on the expression if needed
		if (stmt.expression.getType() == null) {
			stmt.expression.setType(stmt.getType());
		}
		
		if(stmt.expression.getType() != stmt.getType()) {
			this.addError(stmt.label + " expected " + stmt.getType() + " but got " + stmt.expression.getType());
		}
		
		return null;
	}	

	@Override
	public Void visit(IfStmt stmt) {
		//TODO Check if if expression is of type boolean.
		stmt.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		//TODO Check if if expression is of type boolean.
		stmt.ifBlockStmt.accept(this);
		stmt.elseBlockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(BinaryExpr expr) {
		expr.left.accept(this);
		expr.right.accept(this);
		
		if(expr.left.getType() != expr.right.getType()) {
			this.addError("Cannot do " + expr.left.getType() + " " + expr.operator.getLexeme() + " " + expr.right.getType());
		}
		
		return null;
	}

	@Override
	public Void visit(GroupingExpr expr) {
		expr.expression.accept(this);
		return null;
	}

	@Override
	public Void visit(UnaryExpr expr) {
		expr.right.accept(this);
		return null;
	}
	
	@Override
	public Void visit(IdentifierExpr expr) {
		return null;
	}

	@Override
	public Void visit(PrimaryExpr expr) {
		return null;
	}

	@Override
	public Void visit(AdditionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(SubtractionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(MultiplicationExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(DivisionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(LessThanExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(GreaterThanExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(NotEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
