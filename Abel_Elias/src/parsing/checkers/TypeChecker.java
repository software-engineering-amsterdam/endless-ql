package parsing.checkers;

import classes.Question;
import parsing.errors.TypeError;
import parsing.gen.QLParser;
import parsing.visitors.baseVisitor;

import java.util.Date;
import java.util.HashMap;

public class TypeChecker extends baseVisitor {
    // Typechecker checks if there are any inconsistensies in the types that were given in the syntax of the code
    public TypeChecker(HashMap<String, Question> questionMap, QLParser.BlockContext ctx){
        super();
        addQuestions(questionMap);
        visitBlock(ctx);
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();

        if(!haveSameType(visit(ctx.expression()).getClass(), (Class) visit(ctx.type()))){
            throw new TypeError(id, ctx.type().getText());
        }

        return super.visitFixedQuestion(ctx);
    }

    @Override
    public Object visitBoolIdentifier(QLParser.BoolIdentifierContext ctx) {
        String id = ctx.getText();

        if(!haveSameType(Boolean.class, getQuestion(id).getType())){
            throw new TypeError(id, "boolean");
        }

        return super.visitBoolIdentifier(ctx);
    }

    @Override
    public Object visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
        String id = ctx.getText();

        if(!haveSameType(Number.class, getQuestion(id).getType())){
            throw new TypeError(id, "number");
        }

        return super.visitNumIdentifier(ctx);
    }

    private boolean haveSameType(Class value, Class type){
        return value.isAssignableFrom(type);
    }
}
