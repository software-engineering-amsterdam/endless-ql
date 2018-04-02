package org.uva.jomi.ui.interpreter;

import java.util.ArrayList;
import java.util.List;

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

public class IdentifierFinder implements Expression.Visitor<List<String>> {

	public List<String> find(Expression expr) {
		return expr.accept(this);
	}


	@Override
	public List<String> visit(IdentifierExpression expression) {
		List<String> identifiers =  new ArrayList<String>();
		identifiers.add(expression.getName());
		return identifiers;
	}

	@Override
	public List<String> visit(GroupingExpression expression) {
		return expression.visitInnerExpression(this);
	}

	@Override
	public List<String> visit(AdditionExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(SubtractionExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(MultiplicationExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(DivisionExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(LessThanExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(LessThanOrEqualExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(GreaterThanExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(GreaterThanOrEqualExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(NotEqualExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(EqualExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(AndExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(OrExpression expression) {
		return this.combineIdentifiers(expression.visitLeftExpression(this), expression.visitRightExpression(this));
	}

	@Override
	public List<String> visit(UnaryNotExpression expression) {
		return expression.accept(this);
	}

	@Override
	public List<String> visit(IntegerExpression expression) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> visit(StringExpression expression) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> visit(BooleanExpression expression) {
		return new ArrayList<String>();
	}

	private List<String> combineIdentifiers(List<String>firstList, List<String>secondList) {
		for(String identifier : secondList) {
			firstList.add(identifier);
		}
		return firstList;
	}

}
