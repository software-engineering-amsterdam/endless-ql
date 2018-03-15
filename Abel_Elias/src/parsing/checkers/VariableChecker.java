package parsing.checkers;

import classes.Question;
import parsing.Errors.undeclaredVariableError;
import parsing.gen.QLParser;
import parsing.visitors.initVisitor;

import java.util.HashMap;

public class undeclaredVariableChecker extends initVisitor {
    public undeclaredVariableChecker(HashMap<String, Question> questionMap, QLParser.BlockContext ctx){
        super();
        addQuestions(questionMap);
        visitBlock(ctx);
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();

        if(!getQuestions().containsKey(id)) {
            throw new undeclaredVariableError(id);
        }

        return getQuestions().get(id);
    }

    @Override
    public Object visitBlock(QLParser.BlockContext ctx) {
        return new undeclaredVariableChecker(this.getQuestions(), ctx);
    }
}