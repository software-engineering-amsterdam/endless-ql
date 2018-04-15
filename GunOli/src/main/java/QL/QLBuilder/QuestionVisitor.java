package QL.QLBuilder;

import QL.Analysis.EvaluationType;
import QL.AST.Expressions.Expression;
import QL.AST.Expressions.ExpressionConstants.*;
import QL.AST.Question;
import QL.QLAntlrGen.QLBaseVisitor;
import QL.QLAntlrGen.QLParser;

public class QuestionVisitor extends QLBaseVisitor<Question>{
    private Expression condition;

    public QuestionVisitor(Expression condition){
        this.condition = condition;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx){
        int line = ctx.getStart().getLine();
        String name = ctx.IDENTIFIER().getText();
        String text = ctx.STRING().getText();

        QLParser.QuestionTypeContext questionTypeCTX = ctx.questionType();
        String typeText = questionTypeCTX.type().getText();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
        //Format text of type to match EvaluationType declarations
        EvaluationType typeValue = EvaluationType.valueOf(typeText);

        Boolean isPredefined = questionTypeCTX.expression() != null; // checks question is already assigned in form
        Expression initialAnswer = initializeAnswer(questionTypeCTX, typeValue);
        return new Question(name, text, typeValue, initialAnswer, condition, isPredefined, line);
    }

    private Expression initializeAnswer(QLParser.QuestionTypeContext ctx, EvaluationType type){
        int line = ctx.getStart().getLine();
        if(ctx.expression() != null) {
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            return expressionVisitor.visit(ctx.expression());
        }

        switch(type){
            case Boolean:
                return new BooleanConstant(null, line);
            case Date:
                return new DateConstant(null, line);
            case Decimal:
                return new DecimalConstant(null, line);
            case Integer:
                return new IntegerConstant(null, line);
            case Money:
                return new MoneyConstant(null, line);
            case String:
                return new StringConstant(null, line);
            default:
                return new UndefinedConstant(type, line);
        }
    }
}
