package qlviz.interpreter;

import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.numericExpressions.NumericExpression;
import qlviz.model.question.*;

public class QuestionVisitor extends QLBaseVisitor<Question> {

    private final QuestionTypeTranslator questionTypeTranslator;
    private final NumericExpressionParser numericExpressionParser;
    private final BooleanExpressionParser booleanExpressionParser;

    public QuestionVisitor(QuestionTypeTranslator questionTypeTranslator,
                           NumericExpressionParser numericExpressionParser,
                           BooleanExpressionParser booleanExpressionParser) {
        this.questionTypeTranslator = questionTypeTranslator;
        this.numericExpressionParser = numericExpressionParser;
        this.booleanExpressionParser = booleanExpressionParser;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) {
        QuestionType type =
                questionTypeTranslator.translate(ctx.TYPE());
        String text = ctx.questionText().getText();
        String name = ctx.questionName().getText();
        NumericExpression computedValue = null;
        BooleanExpression computedBoolean = null;
        if (ctx.computedValue() != null)
        {
            if (type == QuestionType.Integer || type == QuestionType.Money || type == QuestionType.Decimal){
                computedValue = numericExpressionParser.visitNumericExpression(ctx.computedValue().numericExpression());
            }
            else if (type == QuestionType.Boolean)
            {
                computedBoolean = booleanExpressionParser.visitBooleanExpression(ctx.computedValue().booleanExpression());
            }

        }
        switch (type){
            case Boolean:
                return new BooleanQuestion(name, text, type, computedBoolean, ctx);
            case Money:
                return new MoneyQuestion(name, text, type, computedValue, ctx);
            case String:
                return new StringQuestion(name, text, type, ctx);
            case Integer:
                return new IntegerQuestion(name, text, type, computedValue, ctx);
            case Date:
                return new DateQuestion(name, text, type, ctx);
            case Decimal:
                return new DecimalQuestion(name, text, type, computedValue, ctx);
        }
        return null;
        
    }
}
