package org.uva.jomi.ql.ast.analysis;

import java.util.List;

import org.uva.jomi.ql.ast.QLType;
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
	
	// This method was added for testing purposes
	public String getErrorAtIndex(int index) {
		return errorHandler.getErrorAtIndex(index);
	}
	
	public void resolveBinaryExpr(BinaryExpr expr) {
		// Set the expected type for the left hand side expression and visit it.
		if (expr.getLeftExprType() == null) {
			expr.setLeftExprType(expr.getType());
			expr.getLeftExpr().accept(this);
		} else if (expr.getLeftExprType() != expr.getType()) {
			// We found a type mismatch.
			errorHandler.addTypeError(expr, expr.getLeftExpr());
		}
		
		// Set the expected type for the right hand side expression and visit it.
		if (expr.getRightExprType() == null) {
			expr.setRightExprType(expr.getType());
			expr.getRightExpr().accept(this);
		} else if (expr.getRightExprType() != expr.getType()) {
			// We found a type mismatch.
			errorHandler.addTypeError(expr, expr.getRightExpr());
		}
	}
	
	public void resolveExpr(QLType exprectedType, Expr expr) {
		// Set the expected type for the expression and visit it.
		if (expr.getType() == null) {
			expr.setType(exprectedType);
			expr.accept(this);
		} else if (expr.getType() != exprectedType) {
			// We found a type mismatch.
			errorHandler.addTypeError(exprectedType, expr);
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
		return null;
	}
	
	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		// Before traversing the Ast enforce the question type on the expression if needed
		if (stmt.expression.getType() == null) {
			stmt.expression.setType(stmt.getType());
			stmt.expression.accept(this);
		} else if(stmt.expression.getType() != stmt.getType()) {	
			this.errorHandler.addTypeError(stmt);
		} 

		return null;
	}	

	@Override
	public Void visit(IfStmt stmt) {
		resolveExpr(QLType.BOOLEAN, stmt.expression);
		stmt.blockStmt.accept(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		resolveExpr(QLType.BOOLEAN, stmt.expression);
		stmt.ifBlockStmt.accept(this);
		stmt.elseBlockStmt.accept(this);
		return null;
	}
	
	@Override
	public Void visit(UnaryNotExpr expr) {
		resolveExpr(QLType.BOOLEAN, expr);
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
}