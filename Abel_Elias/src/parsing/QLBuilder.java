package parsing;

import parsing.gen.QLBaseVisitor;
import parsing.gen.QLParser;

public class QLBuilder extends QLBaseVisitor {
    @Override
    public Object visitBlock(QLParser.BlockContext ctx) {
        return super.visitBlock(ctx);
    }

    @Override
    public Object visitQuestion(QLParser.QuestionContext ctx) {
        return super.visitQuestion(ctx);
    }
}
