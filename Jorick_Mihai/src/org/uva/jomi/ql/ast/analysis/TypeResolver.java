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
		statements.forEach( statement -> statement.accept(this));
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
		
		// Check whether the expression an allowed type.
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
	
	@Override
	public Void visit(FormStmt form) {
		form.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStmt block) {
		block.getStatements().forEach( statement -> statement.accept(this));
		return null;
	}

	@Override
	public Void visit(QuestionStmt stmt) {
		return null;
	}
	
	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		stmt.visitExpr(this);
		
		if (stmt.getExprType() != null && stmt.getType() != stmt.getExprType()) {
			this.errorHandler.addTypeError(stmt);
		}
		
		return null;
	}	

	@Override
	public Void visit(IfStmt stmt) {
		stmt.visitExpr(this);
		
		if (stmt.getExprType() != null && stmt.getExprType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(stmt);
		}
		
		stmt.visitIfBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		stmt.visitExpr(this);
		
		if (stmt.getExprType() != null && stmt.getExprType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(stmt);
		}
		
		stmt.visitIfBlockStmt(this);
		stmt.visitElseBlockStmt(this);
		return null;
	}
	
	@Override
	public Void visit(UnaryNotExpr expr) {
		expr.visitRightExpr(this);
		
		if (expr.getRightExprType() != null && expr.getRightExprType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(expr.getRightExpr(), QLType.BOOLEAN);
		}
		
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