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
				if (!"boolean".equals(expression.getType()))
					throw new RuntimeException(
							"Operator not allowed " + ctx.unaryOperator().getText() + " on " + expression.getType());
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
		return new Value("undefined", 0);
	}

	@Override
	public Value visitExpressionMoneyConstant(ExpressionLanguageParser.ExpressionMoneyConstantContext ctx) {

		return new Value("money", ctx.MoneyConstant().getText());
	}

	@Override
	public Value visitExpressionIntegerConstant(ExpressionLanguageParser.ExpressionIntegerConstantContext ctx) {
		return new Value("integer", ctx.IntegerConstant().getText());
	}

	@Override
	public Value visitExpressionBooleanConstant(ExpressionLanguageParser.ExpressionBooleanConstantContext ctx) {
		return new Value("boolean", ctx.BooleanConstant().getText());
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
					ctx.Type().getText());
			answerableQuestions.put(key, question);
			return question;
		}
		return answerableQuestions.get(key);
	}

	@Override
	public Value visitPartComputedQuestion(ExpressionLanguageParser.PartComputedQuestionContext ctx) {

		String identifier = ctx.Identifier().getText();
		String label = ctx.QuotedString().getText();
		String type = ctx.Type().getText();

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

	private boolean allowdOperation(String type1, String type2, String operator) {

		List<String> product = Arrays.asList("*", "/");
		List<String> addition = Arrays.asList("+", "-");
		List<String> logical = Arrays.asList("&&", "||");
		List<String> comparison = Arrays.asList("==", "<=", ">=");

		Set<String> allowedOperators = new HashSet<String>();

		switch (type1 + "-" + type2) {

			case "integer-integer":
				allowedOperators.addAll(product);
				allowedOperators.addAll(addition);
				allowedOperators.addAll(comparison);
				break;

			case "integer-money":
				allowedOperators.addAll(product);
				allowedOperators.addAll(comparison);
				break;

			case "integer-boolean":
				break;

			case "money-integer":
				allowedOperators.addAll(product);
				allowedOperators.addAll(comparison);
				break;

			case "money-money":
				allowedOperators.addAll(addition);
				allowedOperators.addAll(comparison);
				break;

			case "money-boolean":
				break;

			case "boolean-integer":
				break;

			case "boolean-money":
				break;

			case "boolean-boolean":
				allowedOperators.addAll(logical);
				allowedOperators.addAll(comparison);
				break;
		}

		return allowedOperators.contains(operator);

	}

	private String resultType(String type1, String type2, String operator) {

		List<String> addition = Arrays.asList("+", "-");

		switch (type1 + "-" + type2) {

			case "integer-integer":
				return "integer";

			case "integer-money":
				return "money";

			case "integer-boolean":
				return "undefined";

			case "money-integer":
				return "money";

			case "money-money":
				if (addition.contains(operator))
					return "money";
				return "boolean";

			case "money-boolean":
				break;

			case "boolean-integer":
				break;

			case "boolean-money":
				break;

			case "boolean-boolean":
				return "boolean";
		}
		throw new RuntimeException("Check Antlr grammar. Operation impossible: " + type1 + " " + operator + type2);
	}

}
