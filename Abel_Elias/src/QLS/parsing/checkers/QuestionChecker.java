package QLS.parsing.checkers;

import QL.classes.Question;
import QLS.parsing.checkers.errors.NonExistingQuestionError;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.HashMap;

public class QuestionChecker extends QLSBaseVisitor{
    private HashMap<String, Question> questionMap;

    QuestionChecker(HashMap<String, Question> questionMap){
        this.questionMap = questionMap;
    }

    void checkStyleSheet(QLSParser.StylesheetContext stylesheet){
        visitStylesheet(stylesheet);
    }

    @Override
    public Object visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();

        if(!this.questionMap.containsKey(id)){
            throw new NonExistingQuestionError(id);
        }

        return null;
    }
}
