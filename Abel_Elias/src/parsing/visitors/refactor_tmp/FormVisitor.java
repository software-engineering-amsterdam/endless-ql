package parsing.visitors.refactor_tmp;

import classes.CodeBlock;
import classes.Question;
import classes.values.*;
import parsing.checkers.TypeChecker;
import parsing.checkers.VariableChecker;
import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

import java.util.HashMap;

public class FormVisitor extends QLBaseVisitor {
    public HashMap<String, Question> questionMap;
    private BlockVisitor blockVisitor;

    public FormVisitor(QLParser.FormContext form){
        new VariableChecker(form);
        new TypeChecker(form);

        this.questionMap = new HashMap<>();
        this.blockVisitor = new BlockVisitor(questionMap, true);
        visitForm(form);
    }

    // Node visitor
    @Override
    public Object visitForm(QLParser.FormContext ctx) {
        blockVisitor.visitBlock(ctx.block());
        return questionMap;
    }
}