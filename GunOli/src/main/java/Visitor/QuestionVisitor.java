package Visitor;

import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Expressions.Expression;
import ParseObjects.Expressions.ExpressionConstants.*;
import ParseObjects.Question;
import QLAntlrGen.QLBaseVisitor;
import QLAntlrGen.QLParser;

public class QuestionVisitor extends QLBaseVisitor<Question>{
    private ExpressionTable expressionTable;

    public QuestionVisitor(ExpressionTable exprTable){
        super();
        this.expressionTable = exprTable;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx){
        String name = ctx.IDENTIFIER().getText();
        String text = ctx.STRING().getText();

        QLParser.QuestionTypeContext questionTypeCTX = ctx.questionType();
        String typeText = questionTypeCTX.type().getText();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
        //Format text of type to match EvaluationType declarations
        EvaluationType typeValue = EvaluationType.valueOf(typeText);

        Expression initialAnswer = initializeAnswer(questionTypeCTX, typeValue);
        expressionTable.addExpression(name, initialAnswer);
        return new Question(name, text, typeValue, initialAnswer);
    }

    private Expression initializeAnswer(QLParser.QuestionTypeContext ctx, EvaluationType type){
        if(ctx.expression() != null) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor(expressionTable);
            return expressionVisitor.visit(ctx.expression());
        }

        switch(type){
            case Boolean:
                return new BooleanConstant(null);
            case Date:
                return new DateConstant(null);
            case Decimal:
                return new DecimalConstant(null);
            case Integer:
                return new IntegerConstant(null);
            case Money:
                return new MoneyConstant(null);
            case String:
                return new StringConstant(null);
            default:
                return new UndefinedConstant(type);
        }
    }
}
