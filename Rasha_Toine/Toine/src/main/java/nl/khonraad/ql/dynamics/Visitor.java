package nl.khonraad.ql.dynamics;

import java.util.ArrayList;
import java.util.List;

import nl.khonraad.ql.QLBaseVisitor;
import nl.khonraad.ql.QLParser;
import nl.khonraad.ql.algebra.PartialFunction;
import nl.khonraad.ql.algebra.Type;
import nl.khonraad.ql.algebra.Value;

public final class Visitor extends QLBaseVisitor<Value> {

    private Questionnaire questionnaire;

    public Visitor(Questionnaire questionnaire) {
        super();
        this.questionnaire = questionnaire;
    }

    private List<String>        declaredQuestionTypes         = new ArrayList<>();

    private List<String>        forwardReferences             = new ArrayList<>();

    private static final String REFERENCES_UNDEFINED_QUESTION = "Reference to undefined question: ";
    private static final String DUPLICATE_DECLARED            = "Duplicate question declaration: ";
    private static final String TYPE_ERROR                    = "Type error: ";

    private String removeQuotes( String text ) {
        return text.substring( 1, text.length() - 1 );
    }

    @Override
    public Value visitForm( QLParser.FormContext ctx ) {

        declaredQuestionTypes = new ArrayList<>();

        questionnaire.forgetQuestionsRememberAnswers();

        Value value = visitChildren( ctx );

        if ( !forwardReferences.isEmpty() ) { throw new RuntimeException( REFERENCES_UNDEFINED_QUESTION
                + forwardReferences.get( 0 ) ); }

        return value;

    }

    @Override
    public Value visitIdentifier( QLParser.IdentifierContext ctx ) {

        String identifier = ctx.Identifier().getText();

        Question question = questionnaire.findAnswerable( identifier );

        if ( question != null ) {

            forwardReferences.remove( identifier );

            return question.getValue();

        }

        throw new RuntimeException( REFERENCES_UNDEFINED_QUESTION + identifier );
    }

    @Override
    public Value visitPartAnswerableQuestion( QLParser.PartAnswerableQuestionContext ctx ) {

        String identifier = ctx.Identifier().getText();
        String label = removeQuotes( ctx.QuotedString().getText() );

        Type type = Type.parseType( ctx.Type().getText() );

        forwardReferences.remove( identifier );

        if ( declaredQuestionTypes.contains( identifier ) ) { throw new RuntimeException( DUPLICATE_DECLARED
                + identifier + " typed " + type ); }
        declaredQuestionTypes.add( identifier );

        return questionnaire.storeAnswerableQuestion( identifier, label, type );

    }

    @Override
    public Value visitPartComputedQuestion( QLParser.PartComputedQuestionContext ctx ) {

        String identifier = ctx.Identifier().getText();
        String label = removeQuotes( ctx.QuotedString().getText() );

        Type type = Type.parseType( ctx.Type().getText() );

        forwardReferences.remove( identifier );

        Value value = visit( ctx.expression() );

        if ( !type.equals( value.getType() ) ) { throw new RuntimeException( TYPE_ERROR + identifier + " expects "
                + type + " not " + value.getType() ); }

        return questionnaire.storeComputedQuestion( identifier, label, value );
    }

    @Override
    public Value visitUnaryOperator_Expression( QLParser.UnaryOperator_ExpressionContext ctx ) {

        Value expression = visit( ctx.expression() );
        String operator = ctx.unaryOperator().getText();

        try {
            return expression.apply( operator );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }
    }

    @Override
    public Value visitExpressionQuotedString( QLParser.ExpressionQuotedStringContext ctx ) {

        return new Value( Type.String, removeQuotes( ctx.QuotedString().getText() ) );
    }

    @Override
    public Value visitExpression_MultiplicationOperator_Expression(
            QLParser.Expression_MultiplicationOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.multiplicationOperator().getText();

        try {

            new PartialFunction( left, operator ).applyOperand( right );

            return left.apply( operator, right );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    @Override
    public Value visitExpression_AdditionOperator_Expression(
            QLParser.Expression_AdditionOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.additionOperator().getText();

        try {
            return left.apply( operator, right );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    @Override
    public Value visitExpression_EqualityOperator_Expression(
            QLParser.Expression_EqualityOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.equalityOperator().getText();

        try {
            return left.apply( operator, right );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    @Override
    public Value visitExpression_LogicalOperator_Expression(
            QLParser.Expression_LogicalOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.logicalOperator().getText();

        try {
            return left.apply( operator, right );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    @Override
    public Value visitExpression_OrderingOperator_Expression(
            QLParser.Expression_OrderingOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.orderingOperator().getText();

        try {
            return left.apply( operator, right );
        } catch (Exception e) {
            throw new RuntimeException( e.getMessage() );
        }

    }

    @Override
    public Value visitExpressionMoneyConstant( QLParser.ExpressionMoneyConstantContext ctx ) {
        return new Value( Type.Money, ctx.MoneyConstant().getText() );
    }

    @Override
    public Value visitExpressionDateConstant( QLParser.ExpressionDateConstantContext ctx ) {
        return new Value( Type.Date, ctx.DateConstant().getText() );
    }

    @Override
    public Value visitExpressionIntegerConstant( QLParser.ExpressionIntegerConstantContext ctx ) {
        return new Value( Type.Integer, ctx.IntegerConstant().getText() );
    }

    @Override
    public Value visitExpressionBooleanConstant( QLParser.ExpressionBooleanConstantContext ctx ) {
        return new Value( Type.Boolean, ctx.BooleanConstant().getText() );
    }

    @Override
    public Value visitExpressionParenthesized( QLParser.ExpressionParenthesizedContext ctx ) {
        return visit( ctx.expression() );
    }

    @Override
    public Value visitPartConditionalBlock( QLParser.PartConditionalBlockContext ctx ) {

        Value value = visit( ctx.expression() );

        if ( value.equals( Value.TRUE ) ) {
            visitChildren( ctx.block() );
        }
        return value;
    }

}
