package org.uva.jomi.ui.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.expressions.AdditionExpr;
import org.uva.jomi.ql.ast.expressions.AndExpr;
import org.uva.jomi.ql.ast.expressions.BooleanExpr;
import org.uva.jomi.ql.ast.expressions.DivisionExpr;
import org.uva.jomi.ql.ast.expressions.EqualExpr;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.GreaterThanExpr;
import org.uva.jomi.ql.ast.expressions.GreaterThanOrEqualExpr;
import org.uva.jomi.ql.ast.expressions.GroupingExpr;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;
import org.uva.jomi.ql.ast.expressions.IntegerExpr;
import org.uva.jomi.ql.ast.expressions.LessThanExpr;
import org.uva.jomi.ql.ast.expressions.LessThanOrEqualExpr;
import org.uva.jomi.ql.ast.expressions.MultiplicationExpr;
import org.uva.jomi.ql.ast.expressions.NotEqualExpr;
import org.uva.jomi.ql.ast.expressions.OrExpr;
import org.uva.jomi.ql.ast.expressions.StringExpr;
import org.uva.jomi.ql.ast.expressions.SubtractionExpr;
import org.uva.jomi.ql.ast.expressions.UnaryNotExpr;
import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;
import org.uva.jomi.ql.ast.statements.Stmt.Visitor;
import org.uva.jomi.ui.interpreter.value.GenericValue;

public class IdentifierFinder implements Expr.Visitor<List<String>> {

	public List<String> find(Expr expr) {
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
	public List<String> visit(IdentifierExpr expr) {
		List<String> identifiers =  new ArrayList<String>();
		identifiers.add(expr.getName());
		return identifiers;
	}

	@Override
	public List<String> visit(GroupingExpr expr) {
		return expr.visitInnerExpr(this);
	}

	@Override
	public List<String> visit(AdditionExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(SubtractionExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(MultiplicationExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(DivisionExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(LessThanExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(LessThanOrEqualExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(GreaterThanExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(GreaterThanOrEqualExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(NotEqualExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(EqualExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(AndExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(OrExpr expr) {
		return this.combineIdentifiers(expr.visitLeftExpr(this), expr.visitRightExpr(this));
	}

	@Override
	public List<String> visit(UnaryNotExpr expr) {
		return expr.accept(this);
	}

	@Override
	public List<String> visit(IntegerExpr expr) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> visit(StringExpr expr) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> visit(BooleanExpr expr) {
		return new ArrayList<String>();
	}
	
	private List<String> combineIdentifiers(List<String>firstList, List<String>secondList) {
		for(String identifier : secondList) {
			firstList.add(identifier);
		}
		return firstList;
	}

}
