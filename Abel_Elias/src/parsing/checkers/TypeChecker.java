package parsing.checkers;

import classes.Question;
import parsing.checkers.errors.TypeError;
import parsing.gen.QLParser;
import parsing.visitors.BaseVisitor;

import java.util.HashMap;

public class TypeChecker extends BaseVisitor {
    // Typechecker checks if there are any inconsistensies in the types that were given in the syntax of the code
    public TypeChecker(QLParser.FormContext ctx){
        initQuestionMap();
        visitBlock(ctx.block());
    }

    @Override
    public Object visitFixedQuestion(QLParser.FixedQuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Class expectedType = visit(ctx.type()).getClass();
        Class valueType = visit(ctx.expression()).getClass();


        if(!haveSameType(valueType, expectedType)){
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
    public Number visitNumIdentifier(QLParser.NumIdentifierContext ctx) {
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
