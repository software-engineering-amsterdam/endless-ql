package org.uva.jomi.ql.ast;

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

public class AstGraph implements Stmt.Visitor<String>, Expr.Visitor<String> {

	public String getGraph(List<Stmt> statements) {
		String header = "digraph G {\n" + "  node [shape=\"box\"]\n";
		
		for (Stmt statement : statements) {
			String result = statement.accept(this);
			header += result;
		}

		return header + "}";
	}
	
	public String visitPrimaryExpr(PrimaryExpr expr) {
		String primaryExprType = expr.getClass().getSimpleName();
		String value = String.format("Type: %s\nValue: %s\n", primaryExprType, expr.getLexeme());
		return String.format("  %s [label=\"%s\"]\n", expr.getId(), value);
	}
	
	public String visitBinaryExpr(BinaryExpr expr) {
		String binaryExprType = expr.getClass().getSimpleName();
		return expr.visitLeftExpr(this) +
				expr.visitRightExpr(this)+
				String.format("  %s [label=\"%s: %s\nType: %s\n\"]\n", expr.getId(), binaryExprType, expr.getOperatorName(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getId(), expr.getLeftExprId()) +
				String.format("  %s -> %s\n", expr.getId(), expr.getRightExprId());
	}
	

	@Override
	public String visit(IdentifierExpr expr) {
		return String.format("  %s [label=\"[Ident]\nName: %s\nType: %s\nUndefined: %s\"]\n",
				expr.getId(),
				expr.getName(),
				expr.getType(),
				expr.isUndefined());
	}

	@Override
	public String visit(FormStmt stmt) {
		return stmt.visitBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getBlockStmtId()) +
				stmt.visitIndetifierExpr(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getIndetifierExprId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", stmt.getId(), stmt.getIdentifierName());
	}

	@Override
	public String visit(BlockStmt stmt) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", stmt.getId());
		
		for (Stmt statement : stmt.getStatements()) {
			header += String.format("  %s -> %s\n", stmt.getId(), statement.getId());
			String result = statement.accept(this);
			header += result;
		}
		
		return header;
	}

	@Override
	public String visit(QuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
			   stmt.getId(),
			   stmt.getName(),
			   stmt.getType());

		// Visit the identifier expression
		header += stmt.visitIdentifierExpr(this);
		header += String.format("  %s -> %s\n", stmt.getId(), stmt.getIdentifierId());
		
		return header;
	}
	
	@Override
	public String visit(ComputedQuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
				   stmt.getId(),
				   stmt.getName(),
				   stmt.getType());

		// Visit the expression statement
		header += stmt.visitExpr(this);
		header += String.format("  %s -> %s\n", stmt.getId(), stmt.getExpId());

		// Visit the identifier expression
		header += stmt.visitIdentifierExpr(this);
		header += String.format("  %s -> %s\n", stmt.getId(), stmt.getIdentifierId());

		return header;
	}

	@Override
	public String visit(GroupingExpr expr) {
		return expr.visitInnerExpr(this) +
				String.format("  %s [label=\"GroupingExpr\nType: %s\"]\n", expr.getId(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getId(), expr.getInnerExprId());
	}

	@Override
	public String visit(IfStmt stmt) {
		return stmt.visitExpr(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getExprId()) +
				stmt.visitIfBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getIfBlockStmtId()) +
				String.format("  %s [label=\"IfStmt\"]\n", stmt.getId());
	}

	@Override
	public String visit(IfElseStmt stmt) {
		return stmt.visitExpr(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getExprId()) +
				stmt.visitIfBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getIfBlockStmtId()) +
				stmt.visitElseBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getId(), stmt.getElseBlockStmtId()) +
				String.format("  %s [label=\"IfElseStmt\"]\n", stmt.getId());
	}

	@Override
	public String visit(AdditionExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(SubtractionExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(MultiplicationExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(DivisionExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(LessThanExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(LessThanOrEqualExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(GreaterThanExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(GreaterThanOrEqualExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(NotEqualExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(EqualExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(AndExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(OrExpr expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(UnaryNotExpr expr) {
		return 	expr.visitRightExpr(this) +
				String.format("  %s [label=\"UnaryNotExpr: %s\nType: %s\n\"]\n", expr.getId(), expr.getOperatorName(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getId(), expr.getRightExpr().getId());
	}

	@Override
	public String visit(IntegerExpr expr) {
		return visitPrimaryExpr(expr);
	}

	@Override
	public String visit(StringExpr expr) {
		String value = expr.getLexeme().substring(1, expr.getLexeme().length() - 1);
		value = String.format("Type: %s\nValue: %s\n", expr.getType(), value);
		return String.format("  %s [label=\"%s\"]\n", expr.getId(), value);
	}

	@Override
	public String visit(BooleanExpr expr) {
		return visitPrimaryExpr(expr);
	}
}