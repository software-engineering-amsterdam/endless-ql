package org.uva.jomi.ql.ast.analysis;

import java.util.Arrays;
import java.util.List;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.*;
import org.uva.jomi.ql.ast.statements.*;
import org.uva.jomi.ql.error.ErrorHandler;

public class TypeResolver implements Expression.Visitor<QLType>, Statement.Visitor<Void> {

	private final ErrorHandler errorHandler;

	public TypeResolver() {
		this(false);
	}

	public TypeResolver(boolean printErrors) {
		this.errorHandler = new ErrorHandler(this.getClass().getSimpleName(), printErrors);
	}

	public void resolve(List<Statement> statements) {
		// Clear previous errors first
		errorHandler.clearErrors();
		statements.forEach( statement -> statement.accept(this));
	}

	public List<String> getErrors() {
		return errorHandler.getReports();
	}

	public int getNumberOfErrors() {
		return errorHandler.getNumberOfErrors();
	}

	// This method was added for testing purposes
	public String getErrorAtIndex(int index) {
		return errorHandler.getErrorAtIndex(index);
	}

	public boolean binaryExpressionHasEqualTypes(BinaryExpression expression) {
		if (expression.getLeftExpressionType() != expression.getRightExpressionType()) {
			this.errorHandler.addTypeError(expression.getLeftExpression(), expression.getOperator(), expression.getRightExpression());
			return false;
		}

		return true;
	}

	public boolean exprHasAllowedType(Expression expression, QLType ... allowedTypes) {

		// Check whether the expression an allowed type.
		if (!Arrays.asList(allowedTypes).contains(expression.getType())) {
			errorHandler.addTypeError(expression, allowedTypes);
			return false;
		}

		return true;
	}

	public QLType resolveBinaryExpression(BinaryExpression expression, QLType[] validTypes) {
		if (expression.getLeftExpressionType() == null) {
			expression.visitLeftExpression(this);
		}

		if (expression.getRightExpressionType() == null) {
			expression.visitRightExpression(this);
		}

		if (binaryExpressionHasEqualTypes(expression)) {
			if (exprHasAllowedType(expression.getLeftExpression(), validTypes)) {
				expression.setType(expression.getLeftExpressionType());
			} else {
				expression.setType(QLType.UNKNOWN);
			}
		} else {
			expression.setType(QLType.UNKNOWN);
		}

		return expression.getType();
	}

	@Override
	public Void visit(FormStatement statement) {
		statement.visitBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement statement) {
		statement.getStatements().forEach( blockStatement -> blockStatement.accept(this));
		return null;
	}

	@Override
	public Void visit(QuestionStatement statement) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement statement) {

		QLType expressionType = statement.visitExpression(this);
		if (statement.getType() != expressionType) {
			this.errorHandler.addTypeError(statement);
		}

		return null;
	}

	@Override
	public Void visit(IfStatement statement) {
		statement.visitExpression(this);

		if (statement.getExpressionType() != null && statement.getExpressionType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(statement);
		}

		statement.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement statement) {
		statement.visitExpression(this);

		if (statement.getExpressionType() != null && statement.getExpressionType() != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(statement);
		}

		statement.visitIfBlockStatement(this);
		statement.visitElseBlockStatement(this);
		return null;
	}

	@Override
	public QLType visit(UnaryNotExpression expression) {
		QLType rightExpressionType = expression.visitRightExpression(this);

		if (rightExpressionType != QLType.BOOLEAN) {
			this.errorHandler.addTypeError(expression.getRightExpression(), QLType.BOOLEAN);
		}

		expression.setType(rightExpressionType);

		return rightExpressionType;
	}

	@Override
	public QLType visit(GroupingExpression expression) {
		QLType innerExpressionType = expression.visitInnerExpression(this);
		expression.setType(innerExpressionType);
		return innerExpressionType;
	}

	@Override
	public QLType visit(IdentifierExpression expression) {
		QLType expressionType = expression.getType();
		if (expressionType == null) {
			return QLType.UNKNOWN;
		} else {
			return expressionType;
		}
	}

	@Override
	public QLType visit(AdditionExpression expression) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);
		return expression.getType();
	}

	@Override
	public QLType visit(SubtractionExpression expression) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpression(expression, validTypes);
		return expression.getType();
	}

	@Override
	public QLType visit(MultiplicationExpression expression) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpression(expression, validTypes);
		return expression.getType();
	}

	@Override
	public QLType visit(DivisionExpression expression) {
		QLType[] validTypes = { QLType.INTEGER };
		resolveBinaryExpression(expression, validTypes);
		return expression.getType();
	}

	@Override
	public QLType visit(LessThanExpression expression) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(LessThanOrEqualExpression expression) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(GreaterThanExpression expression) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(GreaterThanOrEqualExpression expression) {
		QLType[] validTypes = { QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(NotEqualExpression expression) {
		QLType[] validTypes = { QLType.BOOLEAN, QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(EqualExpression expression) {
		QLType[] validTypes = { QLType.BOOLEAN, QLType.INTEGER, QLType.STRING };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(AndExpression expression) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(OrExpression expression) {
		QLType[] validTypes = { QLType.BOOLEAN };
		resolveBinaryExpression(expression, validTypes);

		// Set the type to boolean before returning
		expression.setType(QLType.BOOLEAN);
		return expression.getType();
	}

	@Override
	public QLType visit(IntegerExpression expression) {
		return expression.getType();
	}

	@Override
	public QLType visit(StringExpression expression) {
		return expression.getType();
	}

	@Override
	public QLType visit(BooleanExpression expression) {
		return expression.getType();
	}
}