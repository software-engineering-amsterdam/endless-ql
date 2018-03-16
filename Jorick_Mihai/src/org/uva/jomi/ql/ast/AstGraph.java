package org.uva.jomi.ql.ast;

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
import org.uva.jomi.ql.ast.expressions.PrimaryExpression;
import org.uva.jomi.ql.ast.expressions.StringExpression;
import org.uva.jomi.ql.ast.expressions.SubtractionExpression;
import org.uva.jomi.ql.ast.expressions.UnaryNotExpression;
import org.uva.jomi.ql.ast.statements.BlockStmt;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.FormStmt;
import org.uva.jomi.ql.ast.statements.IfElseStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;
import org.uva.jomi.ql.ast.statements.QuestionStmt;
import org.uva.jomi.ql.ast.statements.Stmt;

public class AstGraph implements Stmt.Visitor<String>, Expression.Visitor<String> {

	public String getGraph(List<Stmt> statements) {
		String header = "digraph G {\n" + "  node [shape=\"box\"]\n";
		
		for (Stmt statement : statements) {
			String result = statement.accept(this);
			header += result;
		}

		return header + "}";
	}
	
	public String visitPrimaryExpr(PrimaryExpression expr) {
		String primaryExprType = expr.getClass().getSimpleName();
		String value = String.format("Type: %s\nValue: %s\n", primaryExprType, expr.getLexeme());
		return String.format("  %s [label=\"%s\"]\n", expr.getNodeId(), value);
	}
	
	public String visitBinaryExpr(BinaryExpression expr) {
		String binaryExprType = expr.getClass().getSimpleName();
		return expr.visitLeftExpression(this) +
				expr.visitRightExpression(this)+
				String.format("  %s [label=\"%s: %s\nType: %s\n\"]\n", expr.getNodeId(), binaryExprType, expr.getOperatorName(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getNodeId(), expr.getLeftExpressionId()) +
				String.format("  %s -> %s\n", expr.getNodeId(), expr.getRightExpressionId());
	}
	

	@Override
	public String visit(IdentifierExpression expr) {
		return String.format("  %s [label=\"[Ident]\nName: %s\nType: %s\nUndefined: %s\"]\n",
				expr.getNodeId(),
				expr.getName(),
				expr.getType(),
				expr.isUndefined());
	}

	@Override
	public String visit(FormStmt stmt) {
		return stmt.visitBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getBlockStmtId()) +
				stmt.visitIndetifierExpr(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIndetifierExprId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", stmt.getNodeId(), stmt.getIdentifierName());
	}

	@Override
	public String visit(BlockStmt stmt) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", stmt.getNodeId());
		
		for (Stmt statement : stmt.getStatements()) {
			header += String.format("  %s -> %s\n", stmt.getNodeId(), statement.getNodeId());
			String result = statement.accept(this);
			header += result;
		}
		
		return header;
	}

	@Override
	public String visit(QuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
			   stmt.getNodeId(),
			   stmt.getName(),
			   stmt.getType());

		// Visit the identifier expression
		header += stmt.visitIdentifierExpr(this);
		header += String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIdentifierId());
		
		return header;
	}
	
	@Override
	public String visit(ComputedQuestionStmt stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
				   stmt.getNodeId(),
				   stmt.getName(),
				   stmt.getType());

		// Visit the expression statement
		header += stmt.visitExpr(this);
		header += String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getExpId());

		// Visit the identifier expression
		header += stmt.visitIdentifierExpr(this);
		header += String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIdentifierId());

		return header;
	}

	@Override
	public String visit(GroupingExpression expr) {
		return expr.visitInnerExpression(this) +
				String.format("  %s [label=\"GroupingExpr\nType: %s\"]\n", expr.getNodeId(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getNodeId(), expr.getInnerExpressionId());
	}

	@Override
	public String visit(IfStmt stmt) {
		return stmt.visitExpr(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getExprId()) +
				stmt.visitIfBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIfBlockStmtId()) +
				String.format("  %s [label=\"IfStmt\"]\n", stmt.getNodeId());
	}

	@Override
	public String visit(IfElseStmt stmt) {
		return stmt.visitExpr(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getExprId()) +
				stmt.visitIfBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIfBlockStmtId()) +
				stmt.visitElseBlockStmt(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getElseBlockStmtId()) +
				String.format("  %s [label=\"IfElseStmt\"]\n", stmt.getNodeId());
	}

	@Override
	public String visit(AdditionExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(SubtractionExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(MultiplicationExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(DivisionExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(LessThanExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(LessThanOrEqualExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(GreaterThanExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(GreaterThanOrEqualExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(NotEqualExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(EqualExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(AndExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(OrExpression expr) {
		return visitBinaryExpr(expr);
	}

	@Override
	public String visit(UnaryNotExpression expr) {
		return 	expr.visitRightExpression(this) +
				String.format("  %s [label=\"UnaryNotExpr: %s\nType: %s\n\"]\n", expr.getNodeId(), expr.getOperatorName(), expr.getType()) +
				String.format("  %s -> %s\n", expr.getNodeId(), expr.getRightExpression().getNodeId());
	}

	@Override
	public String visit(IntegerExpression expr) {
		return visitPrimaryExpr(expr);
	}

	@Override
	public String visit(StringExpression expr) {
		String value = expr.getLexeme().substring(1, expr.getLexeme().length() - 1);
		value = String.format("Type: %s\nValue: %s\n", expr.getType(), value);
		return String.format("  %s [label=\"%s\"]\n", expr.getNodeId(), value);
	}

	@Override
	public String visit(BooleanExpression expr) {
		return visitPrimaryExpr(expr);
	}
}