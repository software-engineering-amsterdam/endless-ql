package org.uva.jomi.ql.ast.analysis;

import java.util.Arrays;
import java.util.List;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.ErrorHandler;

public class TypeResolver implements Expression.Visitor<QLType>, Statement.Visitor<Void> {

	private final ErrorHandler errorHandler;

	public TypeResolver(boolean printErrors) {
		this.errorHandler = new ErrorHandler(this.getClass().getSimpleName(), printErrors);
	}

	public void resolve(List<Statement> statements) {
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

	public boolean binaryExprHasEqualTypes(BinaryExpression expr) {
		if (expr.getLeftExpressionType() != expr.getRightExpressionType()) {
			this.errorHandler.addTypeError(expr.getLeftExpression(), expr.getOperator(), expr.getRightExpression());
			return false;
		}

		return true;
	}

	public boolean exprHasAllowedType(Expression expr, QLType ... allowedTypes) {

		// Check whether the expression an allowed type.
		if (!Arrays.asList(allowedTypes).contains(expr.getType())) {
			errorHandler.addTypeError(expr, allowedTypes);
			return false;
		}

		return true;
	}

	public QLType resolveBinaryExpr(BinaryExpression expr, QLType[] validTypes) {
		if (expr.getLeftExpressionType() == null) {
			expr.visitLeftExpression(this);
		}

		if (expr.getRightExpressionType() == null) {
			expr.visitRightExpression(this);
		}

		if (binaryExprHasEqualTypes(expr)) {
			if (exprHasAllowedType(expr.getLeftExpression(), validTypes)) {
				expr.setType(expr.getLeftExpressionType());
			} else {
				expr.setType(QLType.UNKNOWN);
			}
		} else {
			expr.setType(QLType.UNKNOWN);
		}

		return expr.getType();
	}

	@Override
	public Void visit(FormStatement form) {
		form.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement block) {
		block.getStatements().forEach( statement -> statement.accept(this));
		return null;
	}

	@Override
	public Void visit(QuestionStatement stmt) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement stmt) {

		QLType expressionType = stmt.visitExpression(this);
		if (stmt.getType() != expressionType) {
			this.errorHandler.addTypeError(stmt);
		}

		return null;
	}

	@Override
	public Void visit(IfStatement stmt) {
		stmt.visitExpression(this);

		if (stmt.getExpressionType() != null && stmt.getExpressionType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(stmt);
		}

		stmt.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement stmt) {
		stmt.visitExpression(this);

		if (stmt.getExpressionType() != null && stmt.getExpressionType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(stmt);
		}

		stmt.visitIfBlockStatement(this);
		stmt.visitElseBlockStatement(this);
		return null;
	}

	@Override
	public QLType visit(UnaryNotExpression expr) {
		QLType expressionType = expr.visitRightExpression(this);

		if (expressionType != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(expr.getRightExpression(), QLType.BOOLEAN);
		}

		expr.setType(expressionType);

		return expressionType;
	}

	@Override
	public QLType visit(GroupingExpression expr) {
		QLType innerExpressionType = expr.visitInnerExpression(this);
		expr.setType(innerExpressionType);
		return expr.visitInnerExpression(this);
	}

	@Override
	public QLType visit(IdentifierExpression expr) {
		if (expr.getType() == null) {
			return QLType.UNKNOWN;
		} else {
			return expr.getType();
		}

	}

	@Override
	public QLType visit(AdditionExpression expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(SubtractionExpression expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(MultiplicationExpression expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(DivisionExpression expr) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpr(expr, validTypes);
		return expr.getType();
	}

	@Override
	public QLType visit(LessThanExpression expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(LessThanOrEqualExpression expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(GreaterThanExpression expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(GreaterThanOrEqualExpression expr) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(NotEqualExpression expr) {
		QLType[] validTypes = { QLType.BOOLEAN, QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(EqualExpression expr) {
		QLType[] validTypes = { QLType.BOOLEAN, QLType.INTEGER, QLType.STRING };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(AndExpression expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(OrExpression expr) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpr(expr, validTypes);

		// Set the type to boolean before returning
		expr.setType(QLType.BOOLEAN);
		return expr.getType();
	}

	@Override
	public QLType visit(IntegerExpression expr) {
		return expr.getType();
	}

	@Override
	public QLType visit(StringExpression expr) {
		return expr.getType();
	}

	@Override
	public QLType visit(BooleanExpression expr) {
		return expr.getType();
	}
}