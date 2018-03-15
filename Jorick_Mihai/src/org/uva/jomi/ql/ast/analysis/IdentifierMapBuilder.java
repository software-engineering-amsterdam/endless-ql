package org.uva.jomi.ql.ast.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.uva.jomi.ql.ast.expressions.AdditionExpr;
import org.uva.jomi.ql.ast.expressions.AndExpr;
import org.uva.jomi.ql.ast.expressions.BinaryExpr;
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

public class IdentifierMapBuilder implements Stmt.Visitor<Void>, Expr.Visitor<List<String>> {
	
	private HashMap<String, List<String>> map;
	
	public IdentifierMapBuilder() {
		this.map = new HashMap<>();
	}
	
	public HashMap<String, List<String>> buildMap(List<Stmt> statements) {
		// Clear the contents of the map.
		this.map.clear();
		statements.forEach( statement -> statement.accept(this));
		return this.map;
	}
	
	private List<String> visitBinaryExpression(BinaryExpr expr) {
		List<String> left = expr.visitLeftExpr(this);
		List<String> right = expr.visitRightExpr(this);
		// Append the contents of the right list to the left one.
		left.addAll(right);
		return left;
	}
	
	@Override
	public Void visit(FormStmt stmt) {
		stmt.visitBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(BlockStmt stmt) {
		stmt.getStatements().forEach( statement -> statement.accept(this));
		return null;
	}

	@Override
	public Void visit(QuestionStmt stmt) {
		this.map.put(stmt.getName(), new ArrayList<>());
		return null;
	}

	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		this.map.put(stmt.getName(), stmt.visitExpr(this));
		return null;
	}

	@Override
	public Void visit(IfStmt stmt) {
		stmt.visitIfBlockStmt(this);
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		stmt.visitIfBlockStmt(this);
		stmt.visitElseBlockStmt(this);
		return null;
	}

	@Override
	public List<String> visit(IdentifierExpr expr) {
		List<String> list = new ArrayList<String>();
		list.add(expr.getName());
		return list;
	}

	@Override
	public List<String> visit(GroupingExpr expr) {
		return expr.visitInnerExpr(this);
	}

	@Override
	public List<String> visit(AdditionExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(SubtractionExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(MultiplicationExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(DivisionExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(LessThanExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(LessThanOrEqualExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(GreaterThanExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(GreaterThanOrEqualExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(NotEqualExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(EqualExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(AndExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(OrExpr expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public List<String> visit(UnaryNotExpr expr) {
		return expr.visitRightExpr(this);
	}

	@Override
	public List<String> visit(IntegerExpr expr) {
		return new ArrayList<>();
	}

	@Override
	public List<String>  visit(StringExpr expr) {
		return new ArrayList<>();
	}

	@Override
	public List<String>  visit(BooleanExpr expr) {
		return new ArrayList<>();
	}


}
