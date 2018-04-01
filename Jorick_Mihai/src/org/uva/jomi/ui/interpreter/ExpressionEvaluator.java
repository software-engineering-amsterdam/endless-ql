	package org.uva.jomi.ui.interpreter;

import org.uva.jomi.ql.ast.expressions.AdditionExpression;
import org.uva.jomi.ql.ast.expressions.AndExpression;
import org.uva.jomi.ql.ast.expressions.BooleanExpression;
import org.uva.jomi.ql.ast.expressions.DivisionExpression;
import org.uva.jomi.ql.ast.expressions.EqualExpression;
import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.GreaterThanExpression;
import org.uva.jomi.ql.ast.expressions.GreaterThanOrEqualExpression;
import org.uva.jomi.ql.ast.expressions.GroupingExpression;
import org.uva.jomi.ql.ast.expressions.IdentifierExpression;
import org.uva.jomi.ql.ast.expressions.IntegerExpression;
import org.uva.jomi.ql.ast.expressions.LessThanExpression;
import org.uva.jomi.ql.ast.expressions.LessThanOrEqualExpression;
import org.uva.jomi.ql.ast.expressions.MultiplicationExpression;
import org.uva.jomi.ql.ast.expressions.NotEqualExpression;
import org.uva.jomi.ql.ast.expressions.OrExpression;
import org.uva.jomi.ql.ast.expressions.StringExpression;
import org.uva.jomi.ql.ast.expressions.SubtractionExpression;
import org.uva.jomi.ql.ast.expressions.UnaryNotExpression;
import org.uva.jomi.ui.interpreter.value.BooleanValue;
import org.uva.jomi.ui.interpreter.value.GenericValue;
import org.uva.jomi.ui.interpreter.value.IntegerValue;
import org.uva.jomi.ui.interpreter.value.StringValue;

public class ExpressionEvaluator implements Expression.Visitor<GenericValue> {

	public GenericValue execute(Expression expression) {
		return expression.accept(this);
	}

	public void catchException() {
		try {

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public GenericValue visit(IdentifierExpression expression) {
		// TODO - Remove dependency on GenericValue
		return SymbolTable.getInstance().get(expression.getName());
	}

	@Override
	public GenericValue visit(GroupingExpression expression) {
		return expression.visitInnerExpression(this);
	}

	@Override
	public GenericValue visit(AdditionExpression expr) {
		GenericValue left = expr.visitLeftExpression(this);
		GenericValue right = expr.visitRightExpression(this);
		return left.add(right);
	}

	@Override
	public GenericValue visit(SubtractionExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.subtract(right);
	}

	@Override
	public GenericValue visit(MultiplicationExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.multiply(right);
	}

	@Override
	public GenericValue visit(DivisionExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.divide(right);
	}

	@Override
	public GenericValue visit(LessThanExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.less(right);
	}

	@Override
	public GenericValue visit(LessThanOrEqualExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.lessOrEqual(right);
	}

	@Override
	public GenericValue visit(GreaterThanExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.greater(right);
	}

	@Override
	public GenericValue visit(GreaterThanOrEqualExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.greaterOrEqual(right);
	}

	@Override
	public GenericValue visit(NotEqualExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.notEqual(right);
	}

	@Override
	public GenericValue visit(EqualExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.equal(right);
	}

	@Override
	public GenericValue visit(AndExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.and(right);
	}

	@Override
	public GenericValue visit(OrExpression expression) {
		GenericValue left = expression.visitLeftExpression(this);
		GenericValue right = expression.visitRightExpression(this);
		return left.or(right);
	}

	@Override
	public GenericValue visit(UnaryNotExpression expression) {
		GenericValue right = expression.visitRightExpression(this);
		return right.negate();
	}

	@Override
	public IntegerValue visit(IntegerExpression expression) {
		return new IntegerValue(expression.getValue());
	}

	@Override
	public StringValue visit(StringExpression expression) {
		return new StringValue(expression.getValue());
	}

	@Override
	public BooleanValue visit(BooleanExpression expression) {
		return new BooleanValue(expression.getValue());
	}

}
