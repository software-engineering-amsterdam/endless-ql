package nl.khonraad.visitors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.khonraad.ExpressionLanguageBaseVisitor;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.domain.AnswerableQuestion;
import nl.khonraad.domain.AsnwerableQuestions;
import nl.khonraad.domain.ComputedQuestion;
import nl.khonraad.domain.ComputedQuestions;
import nl.khonraad.domain.Value;
import nl.khonraad.domain.Type;

public class InterpretingVisitor extends ExpressionLanguageBaseVisitor<Value> {

	public AsnwerableQuestions answerableQuestions = new AsnwerableQuestions();
	public ComputedQuestions computedQuestions = new ComputedQuestions();

	@Override
	public Value visitPartBlock(ExpressionLanguageParser.PartBlockContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Value visitForm(ExpressionLanguageParser.FormContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Value visitUnaryOperator_Expression(ExpressionLanguageParser.UnaryOperator_ExpressionContext ctx) {

		Value expression = visit(ctx.expression());
		String operator = ctx.unaryOperator().getText();

		switch (operator) {

			case "-":
				return new Value(expression.getType(), -expression.getValue());

			case "+":
				return expression;

			case "!":
				if (!Type.Boolean.equals(expression.getType())) {
					throw new RuntimeException(
							"Operator not allowed " + ctx.unaryOperator().getText() + " on " + expression.getType());
				}
				return new Value(expression.getType(), (expression.getValue() != 0) ? 0 : 1);

			default:
				throw new RuntimeException("Undefined operator: \"" + ctx.unaryOperator().getText() + "\"");
		}
	}

	@Override
	public Value visitExpression_BinaryOperator_Expression(
			ExpressionLanguageParser.Expression_BinaryOperator_ExpressionContext ctx) {

		Value left = visit(ctx.expression(0));
		Value right = visit(ctx.expression(1));
		String operator = ctx.binaryOperator().getText();

		if (!allowdOperation(left.getType(), right.getType(), operator)) {

			throw new RuntimeException(
					"Operation not allowed: \"" + left.getType() + " " + operator + " " + right.getType());
		}

		switch (operator) {

			case "*":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() * right.getValue());

			case "/":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() / right.getValue());

			case "+":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() + right.getValue());

			case "-":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() - right.getValue());

			case "&&":
				return new Value(resultType(left.getType(), right.getType(), operator),
						(left.getValue() & right.getValue()) != 0 ? 1 : 0);

			case "||":
				int rv = (left.getValue() | right.getValue()) != 0 ? 1 : 0;
				return new Value(resultType(left.getType(), right.getType(), operator), rv);

			case "==":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() == right.getValue() ? 1 : 0);
			case "<=":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() <= right.getValue() ? 1 : 0);
			case ">=":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() >= right.getValue() ? 1 : 0);
			case "<":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() < right.getValue() ? 1 : 0);
			case ">":
				return new Value(resultType(left.getType(), right.getType(), operator),
						left.getValue() > right.getValue() ? 1 : 0);

			default:
				throw new RuntimeException(
						"Check Antlr grammar. You defined an operator that isn't implemented here: \""
								+ ctx.binaryOperator().getText() + "\"");
		}
	}

	@Override
	public Value visitIdentifier(ExpressionLanguageParser.IdentifierContext ctx) {

		String identifier = ctx.Identifier().getText();

		if (answerableQuestions.containsKey(identifier)) {

			return answerableQuestions.get(identifier);
		}
		return null;
	}

	@Override
	public Value visitExpressionMoneyConstant(ExpressionLanguageParser.ExpressionMoneyConstantContext ctx) {

		return new Value(Type.Money, ctx.MoneyConstant().getText());
	}

	@Override
	public Value visitExpressionIntegerConstant(ExpressionLanguageParser.ExpressionIntegerConstantContext ctx) {
		return new Value(Type.Integer, ctx.IntegerConstant().getText());
	}

	@Override
	public Value visitExpressionBooleanConstant(ExpressionLanguageParser.ExpressionBooleanConstantContext ctx) {
		return new Value(Type.Boolean, ctx.BooleanConstant().getText());
	}

	@Override
	public Value visitExpressionParenthesized(ExpressionLanguageParser.ExpressionParenthesizedContext ctx) {
		return visit(ctx.expression());
	}

	@Override
	public Value visitPartAnswerableQuestion(ExpressionLanguageParser.PartAnswerableQuestionContext ctx) {

		String key = ctx.Identifier().getText();

		if (!answerableQuestions.containsKey(key)) {
			AnswerableQuestion question = new AnswerableQuestion(key, ctx.QuotedString().getText(),
					Type.fromString(ctx.Type().getText()));
			answerableQuestions.put(key, question);
			return question;
		}
		return answerableQuestions.get(key);
	}

	@Override
	public Value visitPartComputedQuestion(ExpressionLanguageParser.PartComputedQuestionContext ctx) {

		String identifier = ctx.Identifier().getText();
		String label = ctx.QuotedString().getText();
		Type type = Type.fromString(ctx.Type().getText());

		Value expression = visit(ctx.expression());

		ComputedQuestion question = new ComputedQuestion(identifier, label, type);
		question.parseAndSetValue(String.valueOf(expression.getValue()));

		computedQuestions.put(identifier, question);

		return new Value(expression.getType(), expression.getValue());
	}

	@Override
	public Value visitPartConditionalBlock(ExpressionLanguageParser.PartConditionalBlockContext ctx) {

		Value value = visit(ctx.expression());

		if (value.getValue() != 0) {
			visitChildren(ctx.block());
		}
		return value;
	}

	private boolean allowdOperation(Type type1, Type type2, String operator) {

		List<String> product = Arrays.asList("*", "/");
		List<String> addition = Arrays.asList("+", "-");
		List<String> logical = Arrays.asList("&&", "||");
		List<String> comparison = Arrays.asList("==", "<=", ">=");

		Set<String> allowedOperators = new HashSet<String>();

		switch (type1.toString() + "-" + type2.toString()) {

			case "Integer-Integer":
				allowedOperators.addAll(product);
				allowedOperators.addAll(addition);
				allowedOperators.addAll(comparison);
				break;

			case "Integer-Money":
				allowedOperators.addAll(product);
				allowedOperators.addAll(comparison);
				break;

			case "Integer-Boolean":
				break;

			case "Money-Integer":
				allowedOperators.addAll(product);
				allowedOperators.addAll(comparison);
				break;

			case "Money-Money":
				allowedOperators.addAll(addition);
				allowedOperators.addAll(comparison);
				break;

			case "Money-Boolean":
				break;

			case "Boolean-Integer":
				break;

			case "Boolean-Money":
				break;

			case "Boolean-Boolean":
				allowedOperators.addAll(logical);
				allowedOperators.addAll(comparison);
				break;
		}

		return allowedOperators.contains(operator);

	}

	private Type resultType(Type type1, Type type2, String operator) {

		List<String> addition = Arrays.asList("+", "-");

		switch (type1.toString() + "-" + type2.toString()) {

			case "Integer-Integer":
				return Type.Integer;

			case "Integer-Money":
				return Type.Money;

			case "Integer-Boolean":
				break;

			case "Money-Integer":
				return Type.Money;

			case "Money-Money":
				if (addition.contains(operator))
					return Type.Money;
				return Type.Boolean;

			case "Money-Boolean":
				break;

			case "Boolean-Integer":
				break;

			case "Boolean-Money":
				break;

			case "Boolean-Boolean":
				return Type.Boolean;
		}
		throw new RuntimeException("Check Antlr grammar. Operation impossible: " + type1 + " " + operator + type2);
	}

}
