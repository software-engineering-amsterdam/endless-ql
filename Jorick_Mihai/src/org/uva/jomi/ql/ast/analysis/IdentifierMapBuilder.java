package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.uva.jomi.ql.ast.expressions.AdditionExpression;
import org.uva.jomi.ql.ast.expressions.AndExpression;
import org.uva.jomi.ql.ast.expressions.BinaryExpression;
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
import org.uva.jomi.ql.ast.statements.BlockStatement;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStatement;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.IfElseStatement;
import org.uva.jomi.ql.ast.statements.IfStatement;
import org.uva.jomi.ql.ast.statements.QuestionStatement;
import org.uva.jomi.ql.ast.statements.Statement;

public class IdentifierMapBuilder implements Statement.Visitor<Void>, Expression.Visitor<List<String>> {

	private HashMap<String, List<String>> map;

	public IdentifierMapBuilder() {
		this.map = new HashMap<>();
	}

	public HashMap<String, List<String>> buildMap(List<Statement> statements) {
		// Clear the contents of the map.
		this.map.clear();
		statements.forEach( statement -> statement.accept(this));
		return this.map;
	}

	private List<String> visitBinaryExpression(BinaryExpression expression) {
		List<String> left = expression.visitLeftExpression(this);
		List<String> right = expression.visitRightExpression(this);
		// Append the contents of the right list to the left one.
		left.addAll(right);
		return left;
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
		this.map.put(statement.getName(), new ArrayList<>());
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement statement) {
		String name = statement.getName();
		if (this.map.containsKey(name)) {
			List<String> list = this.map.get(name);
			list.addAll(statement.visitExpression(this));
			this.map.put(name, list);
		} else {
			this.map.put(name, statement.visitExpression(this));
		}

		return null;
	}

	@Override
	public Void visit(IfStatement statement) {
		statement.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement statement) {
		statement.visitIfBlockStatement(this);
		statement.visitElseBlockStatement(this);
		return null;
	}

	@Override
	public List<String> visit(IdentifierExpression expression) {
		List<String> list = new ArrayList<String>();
		list.add(expression.getName());
		return list;
	}

	@Override
	public List<String> visit(GroupingExpression expression) {
		return expression.visitInnerExpression(this);
	}

	@Override
	public List<String> visit(AdditionExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(SubtractionExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(MultiplicationExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(DivisionExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(LessThanExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(LessThanOrEqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(GreaterThanExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(GreaterThanOrEqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(NotEqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(EqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(AndExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(OrExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public List<String> visit(UnaryNotExpression expression) {
		return expression.visitRightExpression(this);
	}

	@Override
	public List<String> visit(IntegerExpression expression) {
		return new ArrayList<>();
	}

	@Override
	public List<String>  visit(StringExpression expression) {
		return new ArrayList<>();
	}

	@Override
	public List<String>  visit(BooleanExpression expression) {
		return new ArrayList<>();
	}


}
