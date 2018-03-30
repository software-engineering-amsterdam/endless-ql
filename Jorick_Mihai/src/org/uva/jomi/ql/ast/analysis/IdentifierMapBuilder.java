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

	private List<String> visitBinaryExpression(BinaryExpression expr) {
		List<String> left = expr.visitLeftExpression(this);
		List<String> right = expr.visitRightExpression(this);
		// Append the contents of the right list to the left one.
		left.addAll(right);
		return left;
	}

	@Override
	public Void visit(FormStatement stmt) {
		stmt.visitBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(BlockStatement stmt) {
		stmt.getStatements().forEach( statement -> statement.accept(this));
		return null;
	}

	@Override
	public Void visit(QuestionStatement stmt) {
		this.map.put(stmt.getName(), new ArrayList<>());
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStatement stmt) {
		this.map.put(stmt.getName(), stmt.visitExpression(this));
		return null;
	}

	@Override
	public Void visit(IfStatement stmt) {
		stmt.visitIfBlockStatement(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement stmt) {
		stmt.visitIfBlockStatement(this);
		stmt.visitElseBlockStatement(this);
		return null;
	}

	@Override
	public List<String> visit(IdentifierExpression expr) {
		List<String> list = new ArrayList<String>();
		list.add(expr.getName());
		return list;
	}

	@Override
	public List<String> visit(GroupingExpression expr) {
		return expr.visitInnerExpression(this);
	}

	@Override
	public List<String> visit(AdditionExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(SubtractionExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(MultiplicationExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(DivisionExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(LessThanExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(LessThanOrEqualExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(GreaterThanExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(GreaterThanOrEqualExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(NotEqualExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(EqualExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(AndExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(OrExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(UnaryNotExpression expr) {
		return expr.visitRightExpression(this);
	}

	@Override
	public List<String> visit(IntegerExpression expr) {
		return new ArrayList<>();
	}

	@Override
	public List<String>  visit(StringExpression expr) {
		return new ArrayList<>();
	}

	@Override
	public List<String>  visit(BooleanExpression expr) {
		return new ArrayList<>();
	}


}
