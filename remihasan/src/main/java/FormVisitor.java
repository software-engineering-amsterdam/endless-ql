public class FormVisitor extends QLBaseVisitor {

    @Override
    public Object visitRoot(QLParser.RootContext ctx) {
        return super.visitRoot(ctx);
    }

    @Override
    public Object visitQuestion(QLParser.QuestionContext ctx) {
        System.out.println(ctx.questionString().STRING());
        return super.visitQuestion(ctx);
    }

    @Override
    public Object visitQuestionType(QLParser.QuestionTypeContext ctx) {
        System.out.println(ctx.toString());
        return super.visitQuestionType(ctx);
    }
}
