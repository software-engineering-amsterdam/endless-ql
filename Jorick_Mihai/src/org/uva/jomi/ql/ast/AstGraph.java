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

	public String visitPrimaryExpr(PrimaryExpression expression) {
		String primaryExprType = expression.getClass().getSimpleName();
		String value = String.format("Type: %s\nValue: %s\n", primaryExprType, expression.getLexeme());
		return String.format("  %s [label=\"%s\"]\n", expression.getNodeId(), value);
	}

	public String visitBinaryExpression(BinaryExpression expression) {
		String binaryExprType = expression.getClass().getSimpleName();
		return expression.visitLeftExpression(this) +
				expression.visitRightExpression(this)+
				String.format("  %s [label=\"%s: %s\nType: %s\n\"]\n", expression.getNodeId(), binaryExprType, expression.getOperatorName(), expression.getType()) +
				String.format("  %s -> %s\n", expression.getNodeId(), expression.getLeftExpressionId()) +
				String.format("  %s -> %s\n", expression.getNodeId(), expression.getRightExpressionId());
	}


	@Override
	public String visit(IdentifierExpression expression) {
		return String.format("  %s [label=\"[Ident]\nName: %s\nType: %s\nUndefined: %s\"]\n",
				expression.getNodeId(),
				expression.getName(),
				expression.getType(),
				expression.isUndefined());
	}

	@Override
	public String visit(FormStatement statement) {
		return statement.visitBlockStatement(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getBlockStmtId()) +
				statement.visitIndetifierExpr(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getIndetifierExpressionId()) +
				String.format("  %s [label=\"FormStmt\nName: %s\"]\n", statement.getNodeId(), statement.getIdentifierName());
	}

	@Override
	public String visit(BlockStatement statement) {
		String header = String.format("  %s [label=\"BlockStmt\"]\n", statement.getNodeId());

		for (Statement blockStatement : statement.getStatements()) {
			header += String.format("  %s -> %s\n", statement.getNodeId(), blockStatement.getNodeId());
			String result = blockStatement.accept(this);
			header += result;
		}

		return header;
	}

	@Override
	public String visit(QuestionStatement statement) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
			   statement.getNodeId(),
			   statement.getName(),
			   statement.getType());

		// Visit the identifier expression
		header += statement.visitIdentifierExpr(this);
		header += String.format("  %s -> %s\n", statement.getNodeId(), statement.getIdentifierId());

		return header;
	}

	@Override
	public String visit(ComputedQuestionStatement statement) {
		String header = String.format("  %s [label=\"QuestionStmt\nName: %s\nType: %s\"]\n",
				   statement.getNodeId(),
				   statement.getName(),
				   statement.getType());

		// Visit the expression statement
		header += statement.visitExpression(this);
		header += String.format("  %s -> %s\n", statement.getNodeId(), statement.getExpressionId());

		// Visit the identifier expression
		header += statement.visitIdentifierExpr(this);
		header += String.format("  %s -> %s\n", statement.getNodeId(), statement.getIdentifierId());

		return header;
	}

	@Override
	public String visit(GroupingExpression expression) {
		return expression.visitInnerExpression(this) +
				String.format("  %s [label=\"GroupingExpr\nType: %s\"]\n", expression.getNodeId(), expression.getType()) +
				String.format("  %s -> %s\n", expression.getNodeId(), expression.getInnerExpressionId());
	}

	@Override
	public String visit(IfStatement statement) {
		return statement.visitExpression(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getExpressionId()) +
				statement.visitIfBlockStatement(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getIfBlockStatementId()) +
				String.format("  %s [label=\"IfStmt\"]\n", statement.getNodeId());
	}

	@Override
	public String visit(IfElseStatement statement) {
		return statement.visitExpression(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getExpressionId()) +
				statement.visitIfBlockStatement(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getIfBlockStatementId()) +
				statement.visitElseBlockStatement(this) +
				String.format("  %s -> %s\n", statement.getNodeId(), statement.getElseBlockStatementId()) +
				String.format("  %s [label=\"IfElseStmt\"]\n", statement.getNodeId());
	}

	@Override
	public String visit(AdditionExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(SubtractionExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(MultiplicationExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(DivisionExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(LessThanExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(LessThanOrEqualExpression expr) {
		return visitBinaryExpression(expr);
	}

	@Override
	public String visit(GreaterThanExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(GreaterThanOrEqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(NotEqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(EqualExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(AndExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(OrExpression expression) {
		return visitBinaryExpression(expression);
	}

	@Override
	public String visit(UnaryNotExpression expression) {
		return 	expression.visitRightExpression(this) +
				String.format("  %s [label=\"UnaryNotExpr: %s\nType: %s\n\"]\n", expression.getNodeId(), expression.getOperatorName(), expression.getType()) +
				String.format("  %s -> %s\n", expression.getNodeId(), expression.getRightExpression().getNodeId());
	}

	@Override
	public String visit(IntegerExpression expr) {
		return visitPrimaryExpr(expr);
	}

	@Override
	public String visit(StringExpression expression) {
		String value = expression.getLexeme().substring(1, expression.getLexeme().length() - 1);
		value = String.format("Type: %s\nValue: %s\n", expression.getType(), value);
		return String.format("  %s [label=\"%s\"]\n", expression.getNodeId(), value);
	}

	@Override
	public String visit(BooleanExpression expression) {
		return visitPrimaryExpr(expression);
	}
}