package org.uva.jomi.ql.ast.analysis;

import java.util.Arrays;
import java.util.List;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.ErrorHandler;

public class TypeResolver implements Expr.Visitor<QLType>, Stmt.Visitor<Void> {

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

	public QLType resolveBinaryExpr(BinaryExpr expr, QLType[] validTypes) {
		if (expr.getLeftExprType() == null) {
			expr.visitLeftExpr(this);
		}

		if (expr.getRightExprType() == null) {
			expr.visitRightExpr(this);
		}

		if (binaryExprHasEqualTypes(expr)) {
			if (exprHasAllowedType(expr.getLeftExpr(), validTypes)) {
				expr.setType(expr.getLeftExprType());
			} else {
				expr.setType(QLType.UNKNOWN);
			}
		} else {
			expr.setType(QLType.UNKNOWN);
		}

		return expr.getType();
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

		QLType expressionType = stmt.visitExpr(this);
		if (stmt.getType() != expressionType) {
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
	public QLType visit(UnaryNotExpr expr) {
		QLType expressionType = expr.visitRightExpr(this);

		if (expressionType != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(expr.getRightExpr(), QLType.BOOLEAN);
		}

		return expressionType;
	}

	@Override
	public QLType visit(GroupingExpr expr) {
		QLType innerExpressionType = expr.visitInnerExpr(this);
		expr.setType(innerExpressionType);
		return expr.visitInnerExpr(this);
	}

	@Override
	public QLType visit(IdentifierExpr expr) {
		if (expr.getType() == null) {
			return QLType.UNKNOWN;
		} else {
			return expr.getType();
		}

	}

	@Override
	public QLType visit(AdditionExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(SubtractionExpr expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(MultiplicationExpr expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(DivisionExpr expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(LessThanExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(LessThanOrEqualExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(GreaterThanExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(GreaterThanOrEqualExpr expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(NotEqualExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN, QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(EqualExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN, QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(AndExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(OrExpr expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(IntegerExpr expr) {
		return expr.getType();
	}

	@Override
	public QLType visit(StringExpr expr) {
		return expr.getType();
	}

	@Override
	public QLType visit(BooleanExpr expr) {
		return expr.getType();
	}
}