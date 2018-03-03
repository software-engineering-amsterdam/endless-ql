package Visitor;

import ParseObjects.Expressions.EvaluationType;
import ParseObjects.Question;
import antlrGen.QLBaseVisitor;
import antlrGen.QLParser;

public class QuestionVisitor extends QLBaseVisitor<Question>{
    @Override
    public Question visitQuestion(QLParser.QuestionContext ctx){
        String name = ctx.IDENTIFIER().getText();
        String text = ctx.STRING().getText();

        QLParser.QuestionTypeContext questionTypeCTX = ctx.questionType();
        String typeText = questionTypeCTX.type().getText();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
        EvaluationType typeValue = EvaluationType.valueOf(typeText);
        //Todo: Change how the answer is initialized for the new Question
        return new Question(name, text, typeValue, null);
    }
}
