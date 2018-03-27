package qlviz.interpreter;

import com.google.inject.Inject;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.expressions.Expression;
import qlviz.model.question.*;

public class QuestionVisitor extends QLBaseVisitor<Question> {

    private final QuestionTypeTranslator questionTypeTranslator;
    private final NumericExpressionParser numericExpressionParser;
    private final BooleanExpressionParser booleanExpressionParser;

    @Inject
    public QuestionVisitor(QuestionTypeTranslator questionTypeTranslator,
                           NumericExpressionParser numericExpressionParser,
                           BooleanExpressionParser booleanExpressionParser) {
        this.questionTypeTranslator = questionTypeTranslator;
        this.numericExpressionParser = numericExpressionParser;
        this.booleanExpressionParser = booleanExpressionParser;
    }

    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx) throws IllegalArgumentException{
    	
    	QuestionType type =
                questionTypeTranslator.translate(ctx.QUESTION_TYPE());
        String text = ctx.questionText().getText();
        text = text.substring(1, text.length()-1); // Remove ""
        String name = ctx.questionName().getText();
        Expression expression = null;
        if (ctx.computedValue() != null)
        {
            if (ctx.computedValue().numericExpression() != null){
                expression = numericExpressionParser.visitNumericExpression(ctx.computedValue().numericExpression());
            }
            else if (ctx.computedValue().booleanExpression() != null)
            {
                expression = booleanExpressionParser.visitBooleanExpression(ctx.computedValue().booleanExpression());
            }

        }
        switch (type){
            case Boolean:
                return new BooleanQuestion(name, text, type, expression, ctx);
            case Money:
                return new MoneyQuestion(name, text, type, expression, ctx);
            case String:
                return new StringQuestion(name, text, type, ctx);
            case Integer:
                return new IntegerQuestion(name, text, type, expression, ctx);
            case Date:
                return new DateQuestion(name, text, type, ctx);
            case Decimal:
                return new DecimalQuestion(name, text, type, expression, ctx);
        }
        return null;
        
    }
}
