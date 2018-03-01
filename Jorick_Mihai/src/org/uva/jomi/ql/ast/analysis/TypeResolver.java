package org.uva.jomi.ql.ast.analysis;

import java.util.Arrays;
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
	
	public boolean binaryExprHasEqualTypes(BinaryExpr expr) {
		if (expr.getLeftExprType() != expr.getRightExprType()) {
			this.errorHandler.addTypeError(expr.getLeftExpr(), expr.getOperator(), expr.getRightExpr());
			return false;
		}
		
		return true;
	}
	
	public boolean exprHasAllowedType(Expr expr, QLType ... allowedTypes) {
		
		// If the expression is does not have an allowed type 
		if (!Arrays.asList(allowedTypes).contains(expr.getType())) {
			errorHandler.addTypeError(expr, allowedTypes);
			return false;
		}
		
		return true;
	}
	
	public void resolveBinaryExpr(BinaryExpr expr, QLType[] validTypes) {
		if (expr.getLeftExprType() == null) {
			expr.visitLeftExpr(this);
		}
		
		if (expr.getRightExprType() == null) {
			expr.visitRightExpr(this);
		}
		
		if (binaryExprHasEqualTypes(expr)) {
			if (exprHasAllowedType(expr.getLeftExpr(), validTypes)) {
				expr.setType(expr.getLeftExprType());
			}
		}
	}
	
	public void resolveExpr(QLType exprectedType, Expr expr) {
		// Set the expected type for the expression and visit it.
		if (expr.getType() == null) {
			expr.setType(exprectedType);
			expr.accept(this);
		} else if (expr.getType() != exprectedType) {
			// We found a type mismatch.
			//errorHandler.addTypeError(exprectedType, expr);
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
		stmt.expression.accept(this);
		
		if (stmt.expression.getType() != null && stmt.getType() != stmt.expression.getType()) {
			this.errorHandler.addTypeError(stmt);
		}
		
		return null;
	}	

	@Override
	public Void visit(IfStmt stmt) {
		stmt.expression.accept(this);
		
		if (stmt.expression.getType() != null && stmt.expression.getType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(stmt);
		}
		
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
		expr.visitInnerExpr(this);
		return null;
	}
	
	@Override
	public Void visit(IdentifierExpr expr) {
		return null;
	}

	@Override
	public Void visit(AdditionExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(SubtractionExpr expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(MultiplicationExpr expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(DivisionExpr expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(LessThanExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		
		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return null;
	}

	@Override
	public Void visit(LessThanOrEqualExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		
		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return null;
	}

	@Override
	public Void visit(GreaterThanExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		
		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return null;
	}

	@Override
	public Void visit(GreaterThanOrEqualExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		
		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return null;
	}

	@Override
	public Void visit(NotEqualExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		
		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return null;
	}

	@Override
	public Void visit(EqualExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(AndExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(OrExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);
		return null;
	}

	@Override
	public Void visit(IntegerExpr expr) {
		return null;
	}

	@Override
	public Void visit(StringExpr expr) {
		return null;
	}

	@Override
	public Void visit(BooleanExpr expr) {
		return null;
	}
}