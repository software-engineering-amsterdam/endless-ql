package org.uva.jomi.ql.interpreter;

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
import org.uva.jomi.ql.ast.expressions.PrimaryExpr;
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
import org.uva.jomi.ui.SymbolTable;


public class QLInterpreter implements Stmt.Visitor<Void>, Expr.Visitor<Object> {
	
	public void interpret(List<Stmt> statements) {
		for (Stmt statement : statements) {
			execute(statement);
		}
	}
	
	private Object evaluate(Expr expr) {
		return expr.accept(this);
	}
	
	private void execute(Stmt stmt) {
		stmt.accept(this);
	}

	@Override
	public Object visit(IdentifierExpr expr) {
		return SymbolTable.getInstance().get(expr.getName());
	}

	@Override
	public Object visit(GroupingExpr expr) {
		// TODO Interpret GroupingExpr.
		return null;
	}

	@Override
	public Void visit(FormStmt stmt) {
		// TODO Interpret FormStmt.
		return null;
	}

	@Override
	public Void visit(BlockStmt stmt) {
		// TODO Interpret BlockStmt.
		return null;
	}

	@Override
	public Void visit(QuestionStmt stmt) {
		// TODO Interpret QuestionStmt.
		return null;
	}
	
	@Override
	public Void visit(ComputedQuestionStmt stmt) {
		Object value = evaluate(stmt.expression);
		String name = stmt.identifier.getName();
		SymbolTable.getInstance().put(name, value);
		return null;	
	}

	@Override
	public Void visit(IfStmt stmt) {
		// TODO Interpret IfStmt.
		return null;
	}

	@Override
	public Void visit(IfElseStmt stmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AdditionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(SubtractionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MultiplicationExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DivisionExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LessThanExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LessThanOrEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(GreaterThanExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(GreaterThanOrEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(NotEqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(EqualExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AndExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(OrExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnaryNotExpr expr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntegerExpr expr) {
		return expr.getValue();
	}

	@Override
	public Object visit(StringExpr expr) {
		return expr.getValue();
	}

	@Override
	public Object visit(BooleanExpr expr) {
		return expr.getValue();
	}

}
