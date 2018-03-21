package nl.khonraad.qLanguage.ast;

import java.util.ArrayList;
import java.util.List;

import nl.khonraad.qLanguage.QBaseVisitor;
import nl.khonraad.qLanguage.QParser;
import nl.khonraad.qLanguage.domain.Question;
import nl.khonraad.qLanguage.domain.Questionnaire;
import nl.khonraad.qLanguage.domain.Type;
import nl.khonraad.qLanguage.domain.Value;

public final class ParseTreeVisitor extends QBaseVisitor<Value> {

    private Questionnaire questionnaire;

    public ParseTreeVisitor(Questionnaire questionnaire) {
        super();
        this.questionnaire = questionnaire;
    }

    private List<String>        declaredQuestionTypes              = new ArrayList<String>();

    private List<String>        forwardReferences                  = new ArrayList<String>();

    private static final String ERROR_ReferenceToUndefinedQuestion = "Reference to undefined question: ";
    private static final String ERROR_DuplicateQuestionDeclaration = "Duplicate question declaration: ";
    private static final String ERROR_TYPEERROR                    = "Type error: ";

    private String removeQuotes( String text ) {
        return text.substring( 1, text.length() - 1 );
    }

    @Override
    public Value visitForm( QParser.FormContext ctx ) {

        declaredQuestionTypes = new ArrayList<String>();

        questionnaire.forgetQuestionsRememberAnswers();

        Value value = visitChildren( ctx );

        if ( forwardReferences.size() != 0 ) {
            throw new RuntimeException( ERROR_ReferenceToUndefinedQuestion + forwardReferences.get( 0 ) );
        }

        return value;

    }

    @Override
    public Value visitIdentifier( QParser.IdentifierContext ctx ) {

        String identifier = ctx.Identifier().getText();

        Question question = questionnaire.findAnswerable( identifier );

        if ( question != null ) {

            forwardReferences.remove( identifier );

            return question.getValue();

        }

        throw new RuntimeException( ERROR_ReferenceToUndefinedQuestion + identifier );
    }

    @Override
    public Value visitPartAnswerableQuestion( QParser.PartAnswerableQuestionContext ctx ) {

        String identifier = ctx.Identifier().getText();
        String label = removeQuotes( ctx.QuotedString().getText() );

        Type type = Type.parseType( ctx.Type().getText() );

        forwardReferences.remove( identifier );

        if ( declaredQuestionTypes.contains( identifier ) ) {
            throw new RuntimeException( ERROR_DuplicateQuestionDeclaration + identifier + " typed " + type );
        }
        declaredQuestionTypes.add( identifier );

        return questionnaire.storeAnswerableQuestion( identifier, label, type );

    }

    @Override
    public Value visitPartComputedQuestion( QParser.PartComputedQuestionContext ctx ) {

        String identifier = ctx.Identifier().getText();
        String label = removeQuotes( ctx.QuotedString().getText() );

        Type type = Type.parseType( ctx.Type().getText() );

        forwardReferences.remove( identifier );

        Value value = visit( ctx.expression() );

        if ( !type.equals( value.getType() ) ) {
            throw new RuntimeException( ERROR_TYPEERROR + identifier + " expects " + type + " not " + value.getType() );
        }

        return questionnaire.storeComputedQuestion( identifier, label, value );
    }

    @Override
    public Value visitPartBlock( QParser.PartBlockContext ctx ) {
        return visitChildren( ctx );
    }

    @Override
    public Value visitUnaryOperator_Expression( QParser.UnaryOperator_ExpressionContext ctx ) {

        Value expression = visit( ctx.expression() );
        String operator = ctx.unaryOperator().getText();

        switch ( operator ) {

            case "-":
                return expression.apply( operator );

            case "+":
                return expression;

            case "!":
                return expression.apply( operator );

            default:
                throw new RuntimeException( "Undefined operator: \"" + ctx.unaryOperator().getText() + "\"" );
        }
    }

    @Override
    public Value visitExpressionQuotedString( QParser.ExpressionQuotedStringContext ctx ) {

        return new Value( Type.String, removeQuotes( ctx.QuotedString().getText() ) );
    }

    @Override
    public Value visitExpression_BinaryOperator_Expression( QParser.Expression_BinaryOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.binaryOperator().getText();

        return left.apply( operator, right );

    }

    @Override
    public Value visitExpressionMoneyConstant( QParser.ExpressionMoneyConstantContext ctx ) {
        return new Value( Type.Money, ctx.MoneyConstant().getText() );
    }

    @Override
    public Value visitExpressionDateConstant( QParser.ExpressionDateConstantContext ctx ) {
        return new Value( Type.Date, ctx.DateConstant().getText() );
    }

    @Override
    public Value visitExpressionIntegerConstant( QParser.ExpressionIntegerConstantContext ctx ) {
        return new Value( Type.Integer, ctx.IntegerConstant().getText() );
    }

    @Override
    public Value visitExpressionBooleanConstant( QParser.ExpressionBooleanConstantContext ctx ) {
        return new Value( Type.Boolean, ctx.BooleanConstant().getText() );
    }

    @Override
    public Value visitExpressionParenthesized( QParser.ExpressionParenthesizedContext ctx ) {
        return visit( ctx.expression() );
    }

    @Override
    public Value visitPartConditionalBlock( QParser.PartConditionalBlockContext ctx ) {

        Value value = visit( ctx.expression() );

        if ( value.equals( Value.TRUE ) ) {
            visitChildren( ctx.block() );
        }
        return value;
    }

}
