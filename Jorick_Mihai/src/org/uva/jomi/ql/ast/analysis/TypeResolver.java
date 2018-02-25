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
	
	public void resolveBinaryExpr(BinaryExpr expr) {
		// Set the expected type for the left hand side expression and visit it.
		if (expr.getLeftExpr().getType() == null) {
			expr.getLeftExpr().setType(expr.getType());
			expr.getLeftExpr().accept(this);
		}
		
		// Set the expected type for the right hand side expression and visit it.
		if (expr.getRightExpr().getType() == null) {
			expr.getRightExpr().setType(expr.getType());
			expr.getRightExpr().accept(this);
		}
		
		// We now assume that both the left and the right hand side expressions have a type.
		
		if (expr.getLeftExpr().getType() != expr.getType() ||
			expr.getRightExpr().getType() != expr.getType()) {
			this.errorHandler.addTypeError(expr, expr.getLeftExpr(), expr.getRightExpr());
		}
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
	public Void visit(GroupingExpr expr) {
		expr.getExpression().accept(this);
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
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(SubtractionExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(MultiplicationExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(DivisionExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(LessThanExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(GreaterThanExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(NotEqualExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(EqualExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(AndExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(OrExpr expr) {
		resolveBinaryExpr(expr);
		return null;
	}

	@Override
	public Void visit(UnaryNotExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}
	
}