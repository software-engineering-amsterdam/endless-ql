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
import org.uva.jomi.ql.ast.statements.BlockStatement;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStatement;
import org.uva.jomi.ql.ast.statements.FormStatement;
import org.uva.jomi.ql.ast.statements.IfElseStatement;
import org.uva.jomi.ql.ast.statements.IfStatement;
import org.uva.jomi.ql.ast.statements.QuestionStatement;
import org.uva.jomi.ql.ast.statements.Statement;

public class AstGraph implements Statement.Visitor<String>, Expression.Visitor<String> {

	public String getGraph(List<Statement> statements) {
		String header = "digraph G {\n" + "  node [shape=\"box\"]\n";

		for (Statement statement : statements) {
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
	public String visit(FormStatement stmt) {
		return stmt.visitBlockStatement(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getBlockStmtId()) +
				stmt.visitIndetifierExpr(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIndetifierExpressionId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", stmt.getNodeId(), stmt.getIdentifierName());
	}

	@Override
	public String visit(BlockStatement stmt) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", stmt.getNodeId());

		for (Statement statement : stmt.getStatements()) {
			header += String.format("  %s -> %s\n", stmt.getNodeId(), statement.getNodeId());
			String result = statement.accept(this);
			header += result;
		}

		return header;
	}

	@Override
	public String visit(QuestionStatement stmt) {
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
	public String visit(ComputedQuestionStatement stmt) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
				   stmt.getNodeId(),
				   stmt.getName(),
				   stmt.getType());

		// Visit the expression statement
		header += stmt.visitExpression(this);
		header += String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getExpressionId());

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
	public String visit(IfStatement stmt) {
		return stmt.visitExpression(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getExpressionId()) +
				stmt.visitIfBlockStatement(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIfBlockStatementId()) +
				String.format("  %s [label=\"IfStmt\"]\n", stmt.getNodeId());
	}

	@Override
	public String visit(IfElseStatement stmt) {
		return stmt.visitExpression(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getExpressionId()) +
				stmt.visitIfBlockStatement(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getIfBlockStatementId()) +
				stmt.visitElseBlockStatement(this) +
				String.format("  %s -> %s\n", stmt.getNodeId(), stmt.getElseBlockStatementId()) +
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