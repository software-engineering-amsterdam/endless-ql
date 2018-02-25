package org.uva.jomi.ql.ast.analysis;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.ErrorHandler;

public class TypeResolver implements Expr.Visitor<Void>, Stmt.Visitor<Void> {
	
	private final ErrorHandler errorHandler;
	
	public TypeResolver(boolean printErrors) {
		this.errorHandler = new ErrorHandler(this.getClass().getSimpleName(), printErrors);
	}
	
	public void resolve(List<Stmt> statements) {
		// Clear previous errors first
		errorHandler.clearErrors();
		
		for (Stmt statement : statements) {
			statement.accept(this);
		}
	}
	
	public int getNumberOfErrors() {
		return errorHandler.getNumberOfErrors();
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
		// Before traversing the Ast enforce the question type on the expression if needed
		if (stmt.expression.getType() == null) {
			stmt.expression.setType(stmt.getType());
			stmt.expression.accept(this);
		}
		
		if(stmt.expression.getType() != stmt.getType()) {
			
			this.errorHandler.addTypeError(stmt);
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
			this.errorHandler.addTypeError(expr, expr.left, expr.right);
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
		// Set the expected type for the left hand side expression and visit it.
		if (expr.left.getType() == null) {
			expr.left.setType(expr.getType());
			expr.left.accept(this);
		}
		
		// Set the expected type for the right hand side expression and visit it.
		if (expr.right.getType() == null) {
			expr.right.setType(expr.getType());
			expr.right.accept(this);
		}
		
		// We now assume that both the left and the right hand side expressions have a type.
		
		if (expr.left.getType() != expr.getType()) {
			this.errorHandler.addTypeError(expr, expr.left, expr.right);
		}
		
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

	@Override
	public Void visit(EqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(AndExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(OrExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(UnaryNotExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}
	
}