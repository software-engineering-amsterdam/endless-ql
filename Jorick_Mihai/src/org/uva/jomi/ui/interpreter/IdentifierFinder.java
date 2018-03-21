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

	/*
	@Override
	public List<String> visit(FormStmt stmt) {
		return stmt.getBlockStmt().accept(this);
	}

	@Override
	public List<String> visit(BlockStmt stmt) {
		List<String> identifiers = new ArrayList<String>();
		for (Stmt statement : stmt.getStatements()) {
			identifiers = this.combineIdentifiers(identifiers, statement.accept(this));
		}
		return identifiers;
	}

	@Override
	public List<String> visit(QuestionStmt stmt) {
		List<String> identifiers =  new ArrayList<String>();
		identifiers.add(stmt.getIdentifierName());
		return identifiers;
	}

	@Override
	public List<String> visit(ComputedQuestionStmt stmt) {
		List<String> identifiers =  new ArrayList<String>();
		identifiers.add(stmt.getIdentifierName());
		return identifiers;
	}

	@Override
	public List<String> visit(IfStmt stmt) {
		return stmt.getIfBlockStmt().accept(this);
	}

	@Override
	public List<String> visit(IfElseStmt stmt) {
		List<String> identifiers = stmt.getIfBlockStmt().accept(this);
		return this.combineIdentifiers(identifiers, stmt.getElseBlockStmt().accept(this));
	}
	*/


	@Override
	public List<String> visit(IdentifierExpression expr) {
		List<String> identifiers =  new ArrayList<String>();
		identifiers.add(expr.getName());
		return identifiers;
	}

	@Override
	public List<String> visit(GroupingExpression expr) {
		return expr.visitInnerExpression(this);
	}

	@Override
	public List<String> visit(AdditionExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(SubtractionExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(MultiplicationExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(DivisionExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(LessThanExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(LessThanOrEqualExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(GreaterThanExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(GreaterThanOrEqualExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(NotEqualExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(EqualExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(AndExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(OrExpression expr) {
		return this.combineIdentifiers(expr.visitLeftExpression(this), expr.visitRightExpression(this));
	}

	@Override
	public List<String> visit(UnaryNotExpression expr) {
		return expr.accept(this);
	}

	@Override
	public List<String> visit(IntegerExpression expr) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> visit(StringExpression expr) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> visit(BooleanExpression expr) {
		return new ArrayList<String>();
	}

	private List<String> combineIdentifiers(List<String>firstList, List<String>secondList) {
		for(String identifier : secondList) {
			firstList.add(identifier);
		}
		return firstList;
	}

}
