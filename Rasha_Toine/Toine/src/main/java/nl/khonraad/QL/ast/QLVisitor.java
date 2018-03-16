package nl.khonraad.QL.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.khonraad.QL.ExpressionLanguageBaseVisitor;
import nl.khonraad.QL.ExpressionLanguageParser;
import nl.khonraad.QL.domain.Form;
import nl.khonraad.QL.domain.Question;
import nl.khonraad.QL.domain.Type;
import nl.khonraad.QL.domain.Value;
import nl.khonraad.QL.domain.Question.BehaviouralType;

public class QLVisitor extends ExpressionLanguageBaseVisitor<Value> {

    private List<String>        declaredQuestionTypes              = new ArrayList<String>();

    public Form                 form                               = new Form();
    private List<String>        forwardReferences                  = new ArrayList<String>();

    private static final String ERROR_ReferenceToUndefinedQuestion = "Reference to undefined question: ";
    private static final String ERROR_DuplicateQuestionDeclaration = "Duplicate question declaration: ";
    private static final String ERROR_TYPEERROR                    = "Type error: ";

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

        form.forgetQuestionsRememberAnswers();

        Value value = visitChildren( ctx );

        if ( forwardReferences.size() != 0 ) {
            throw new RuntimeException( ERROR_ReferenceToUndefinedQuestion + forwardReferences.get( 0 ) );
        }

        return value;

    }

    @Override
    public Value visitUnaryOperator_Expression( ExpressionLanguageParser.UnaryOperator_ExpressionContext ctx ) {

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
    public Value visitExpressionQuotedString( ExpressionLanguageParser.ExpressionQuotedStringContext ctx ) {

        return new Value( Type.String, removeQuotes( ctx.QuotedString().getText() ) );
    }

    @Override
    public Value visitExpression_BinaryOperator_Expression(
            ExpressionLanguageParser.Expression_BinaryOperator_ExpressionContext ctx ) {

        Value left = visit( ctx.expression( 0 ) );
        Value right = visit( ctx.expression( 1 ) );
        String operator = ctx.binaryOperator().getText();

        return left.apply( operator, right );

    }

    @Override
    public Value visitIdentifier( ExpressionLanguageParser.IdentifierContext ctx ) {

        String identifier = ctx.Identifier().getText();

        Optional<Question> optionalQuestion = form.findQuestion( BehaviouralType.ANSWERABLE, identifier );

        if ( optionalQuestion.isPresent() ) {

            forwardReferences.remove( identifier );

            return optionalQuestion.get().getValue();

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

        String identifier = ctx.Identifier().getText();
        String label = removeQuotes( ctx.QuotedString().getText() );

        forwardReferences.remove( identifier );

        Type type = Type.parseType( ctx.Type().getText() );

        if ( declaredQuestionTypes.contains( identifier ) ) {
            throw new RuntimeException( ERROR_DuplicateQuestionDeclaration + identifier + " typed " + type );
        }
        declaredQuestionTypes.add( identifier );

        return form.saveAnswerableQuestion( identifier, label, type );

    }

    @Override
    public Value visitPartComputedQuestion( ExpressionLanguageParser.PartComputedQuestionContext ctx ) {

        String identifier = ctx.Identifier().getText();
        String label = removeQuotes( ctx.QuotedString().getText() );

        Type type = Type.parseType( ctx.Type().getText() );

        forwardReferences.remove( identifier );

        Value value = visit( ctx.expression() );

        if ( !type.equals( value.getType() ) ) {
            throw new RuntimeException( ERROR_TYPEERROR + identifier + " expects " + type + " not " + value.getType() );
        }

        return form.saveComputedQuestion( identifier, label, type, value );
    }

    @Override
    public Value visitPartConditionalBlock( ExpressionLanguageParser.PartConditionalBlockContext ctx ) {

        Value value = visit( ctx.expression() );

        if ( value.equals( new Value( Type.Boolean, "True" ) ) ) {
            visitChildren( ctx.block() );
        }
        return value;
    }

}
