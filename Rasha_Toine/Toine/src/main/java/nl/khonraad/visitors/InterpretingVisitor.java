package nl.khonraad.visitors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.khonraad.ExpressionLanguageBaseVisitor;
import nl.khonraad.ExpressionLanguageParser;
import nl.khonraad.domain.AnswerableQuestion;
import nl.khonraad.domain.ComputedQuestion;
import nl.khonraad.domain.Value;
import nl.khonraad.domain.Type;

public class InterpretingVisitor extends ExpressionLanguageBaseVisitor<Value> {

	public List<String> declaredQuestionTypes = new ArrayList<String>();
	public Map<String, AnswerableQuestion> answerableQuestions = new HashMap<String, AnswerableQuestion>();
	public Map<String, ComputedQuestion> computedQuestions = new HashMap<String, ComputedQuestion>();

	public List<String> forwardReferences = new ArrayList<String>();

	public static final String ERROR_ReferenceToUndefinedQuestion = "Reference to undefined question: ";
	public static final String ERROR_DuplicateQuestionDeclaration = "Duplicate question declaration: ";
	public static final String ERROR_TYPEERROR = "Type error: ";

	private String removeQuotes( String text ) {
		return text.substring( 1, text.length() - 1 );
	}

	@Override
	public Value visitPartBlock( ExpressionLanguageParser.PartBlockContext ctx ) {
		return visitChildren( ctx );
	}

	@Override
	public Value visitForm( ExpressionLanguageParser.FormContext ctx ) {

		declaredQuestionTypes = new ArrayList<String>();

		Value value = visitChildren( ctx );

		if (forwardReferences.size() != 0) {
			throw new RuntimeException( ERROR_ReferenceToUndefinedQuestion + forwardReferences.get( 0 ) );
		}

		return value;

	}

	@Override
	public Value visitUnaryOperator_Expression( ExpressionLanguageParser.UnaryOperator_ExpressionContext ctx ) {

		Value expression = visit( ctx.expression() );
		String operator = ctx.unaryOperator().getText();

		switch (operator) {

			case "-":
				return new Value( expression.getType(), -expression.getUnits() );

			case "+":
				return expression;

			case "!":
				if (!Type.Boolean.equals( expression.getType() )) {
					throw new RuntimeException(
							"Operator not allowed " + ctx.unaryOperator().getText() + " on " + expression.getType() );
				}
				return new Value( expression.getType(), (expression.getUnits() != 0) ? 0 : 1 );

			default:
				throw new RuntimeException( "Undefined operator: \"" + ctx.unaryOperator().getText() + "\"" );
		}
	}

	@Override
	public Value visitExpressionQuotedString( ExpressionLanguageParser.ExpressionQuotedStringContext ctx ) {

		return new Value( Type.String, removeQuotes( ctx.String().getText() ) );
	}

	@Override
	public Value visitExpression_BinaryOperator_Expression(
			ExpressionLanguageParser.Expression_BinaryOperator_ExpressionContext ctx ) {

		Value left = visit( ctx.expression( 0 ) );
		Value right = visit( ctx.expression( 1 ) );
		String operator = ctx.binaryOperator().getText();

		Type leftType = left.getType();
		Type rightType = right.getType();

		long leftOperand = left.getUnits();
		long rightOperand = right.getUnits();

		if (!allowdOperation( leftType, rightType, operator )) {

			throw new RuntimeException( "Operation not allowed: \"" + leftType + " " + operator + " " + rightType );
		}

		Type resultType = resultType( leftType, rightType, operator );

		switch (operator) {

			case "*":
				return new Value( resultType, leftOperand * rightOperand );

			case "/":
				return new Value( resultType, leftOperand / rightOperand );

			case "+":
				switch (resultType) {
					case String:
						return new Value( resultType, left.getText() + right.getText() );
					default: 	
						return new Value( resultType, leftOperand + rightOperand );
				}

			case "-":
				return new Value( resultType, leftOperand - rightOperand );

			case "&&":
				return new Value( resultType, (leftOperand & rightOperand) != 0 ? 1 : 0 );

			case "||":
				return new Value( resultType, (leftOperand | rightOperand) != 0 ? 1 : 0 );

			case "==":
				return new Value( resultType, leftOperand == rightOperand ? 1 : 0 );
			case "<=":
				return new Value( resultType, leftOperand <= rightOperand ? 1 : 0 );
			case ">=":
				return new Value( resultType, leftOperand >= rightOperand ? 1 : 0 );
			case "<":
				return new Value( resultType, leftOperand < rightOperand ? 1 : 0 );
			case ">":
				return new Value( resultType, leftOperand > rightOperand ? 1 : 0 );

			default:
				throw new RuntimeException(
						"Check Antlr grammar. You defined an operator that isn't implemented here: \""
								+ ctx.binaryOperator().getText() + "\"" );
		}
	}

	@Override
	public Value visitIdentifier( ExpressionLanguageParser.IdentifierContext ctx ) {

		String identifier = ctx.Identifier().getText();

		if (answerableQuestions.containsKey( identifier )) {

			forwardReferences.remove( identifier );
			return answerableQuestions.get( identifier ).getValue();

		}

		if (computedQuestions.containsKey( identifier )) {

			forwardReferences.remove( identifier );
			return computedQuestions.get( identifier ).getValue();

		}

		throw new RuntimeException( ERROR_ReferenceToUndefinedQuestion + identifier );
	}

	@Override
	public Value visitExpressionMoneyConstant( ExpressionLanguageParser.ExpressionMoneyConstantContext ctx ) {

		return new Value( Type.Money, ctx.MoneyConstant().getText() );
	}

	@Override
	public Value visitExpressionDateConstant( ExpressionLanguageParser.ExpressionDateConstantContext ctx ) {
		return new Value( Type.Date, ctx.DateConstant().getText() );
	}

	@Override
	public Value visitExpressionIntegerConstant( ExpressionLanguageParser.ExpressionIntegerConstantContext ctx ) {
		return new Value( Type.Integer, ctx.IntegerConstant().getText() );
	}

	@Override
	public Value visitExpressionBooleanConstant( ExpressionLanguageParser.ExpressionBooleanConstantContext ctx ) {
		return new Value( Type.Boolean, ctx.BooleanConstant().getText() );
	}

	@Override
	public Value visitExpressionParenthesized( ExpressionLanguageParser.ExpressionParenthesizedContext ctx ) {
		return visit( ctx.expression() );
	}

	@Override
	public Value visitPartAnswerableQuestion( ExpressionLanguageParser.PartAnswerableQuestionContext ctx ) {

		String identifier =  ctx.Identifier().getText();
		String label = removeQuotes( ctx.String().getText());

		forwardReferences.remove( identifier );

		Type type = Type.parseType( ctx.Type().getText() );

		AnswerableQuestion question = new AnswerableQuestion( identifier, ctx.String().getText(), type );

		if (declaredQuestionTypes.contains( identifier )) {

			throw new RuntimeException( ERROR_DuplicateQuestionDeclaration + identifier + " typed " + type );

		}
		declaredQuestionTypes.add( identifier );

		if (!answerableQuestions.containsKey( identifier )) {
			answerableQuestions.put( identifier, question );
		}

		return answerableQuestions.get( identifier ).getValue();
	}

	@Override
	public Value visitPartComputedQuestion( ExpressionLanguageParser.PartComputedQuestionContext ctx ) {

		String identifier =  ctx.Identifier().getText();
		String label = removeQuotes(ctx.String().getText());;
		Type type = Type.parseType( ctx.Type().getText() );

		forwardReferences.remove( identifier );

		Value newValue = visit( ctx.expression() );
		if (!type.equals( newValue.getType() )) {
			throw new RuntimeException(
					ERROR_TYPEERROR + identifier + " expects " + type + " not " + newValue.getType() );
		}

		ComputedQuestion question = new ComputedQuestion( identifier, label, newValue );

		computedQuestions.put( identifier, question );

		if (declaredQuestionTypes.contains( identifier )) {

			throw new RuntimeException( ERROR_DuplicateQuestionDeclaration + identifier + " typed " + type );

		}
		declaredQuestionTypes.add( identifier );
		return newValue;
	}

	@Override
	public Value visitPartConditionalBlock( ExpressionLanguageParser.PartConditionalBlockContext ctx ) {

		Value value = visit( ctx.expression() );
		if (value.getUnits() != 0) {
			visitChildren( ctx.block() );
		}
		return value;
	}

	static List<String> PRODUCT_OPERATORS = Arrays.asList( "*", "/" );
	static List<String> ADDITION_OPERATORS = Arrays.asList( "+", "-" );
	static List<String> LOGICAL_OPERATORS = Arrays.asList( "&&", "||" );
	static List<String> COMPARISON_OPERATORS = Arrays.asList( "==", "<=", ">=", "<", ">" );

	private boolean allowdOperation( Type type1, Type type2, String operator ) {

		Set<String> allowedOperators = new HashSet<String>();

		switch (type1 + "-" + type2) {

			case "Integer-Integer":
				allowedOperators.addAll( PRODUCT_OPERATORS );
				allowedOperators.addAll( ADDITION_OPERATORS );
				allowedOperators.addAll( COMPARISON_OPERATORS );
				break;

			case "Money-Money":
				allowedOperators.addAll( ADDITION_OPERATORS );
				allowedOperators.addAll( COMPARISON_OPERATORS );
				break;

			case "Boolean-Boolean":
				allowedOperators.addAll( LOGICAL_OPERATORS );
				allowedOperators.addAll( COMPARISON_OPERATORS );
				break;

			case "String-String":
				allowedOperators.addAll( ADDITION_OPERATORS );
				allowedOperators.addAll( COMPARISON_OPERATORS );
				break;

			case "Date-Date":
				allowedOperators.addAll( LOGICAL_OPERATORS );
				allowedOperators.addAll( COMPARISON_OPERATORS );
				break;

			case "Integer-Money":
			case "Money-Integer":
				allowedOperators.addAll( PRODUCT_OPERATORS );
				allowedOperators.addAll( COMPARISON_OPERATORS );
				break;

			case "Integer-Date":
			case "Date-Integer":
				allowedOperators.addAll( ADDITION_OPERATORS );
				break;

		}

		return allowedOperators.contains( operator );

	}

	private Type resultType( Type type1, Type type2, String operator ) {

		List<String> addition = Arrays.asList( "+", "-" );

		switch (type1 + "-" + type2) {

			case "Integer-Integer":
				return Type.Integer;

			case "Money-Money":
				if (addition.contains( operator )) {
					return Type.Money;
				}
				return Type.Boolean;

			case "Boolean-Boolean":
				return Type.Boolean;

			case "String-String":
				return Type.String;

			case "Date-Date":
				return Type.Date;

			case "Integer-Money":
			case "Money-Integer":
				return Type.Money;

			case "Integer-Date":
			case "Date-Integer":
				return Type.Date;

		}
		throw new RuntimeException( "Check Antlr grammar. Operation impossible: " + type1 + " " + operator + type2 );
	}

}
