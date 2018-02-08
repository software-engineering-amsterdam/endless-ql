public class QlQuestionListener extends QLBaseListener {

    @Override
    public void enterQuestion(QLParser.QuestionContext ctx) {
        System.out.println("QUESTION:");
        System.out.println(ctx.questionString().getText());

        System.out.println("PARSING QUESTION...");


        Question question = new Question();
        QuestionDatabase.getInstance().addQuestion(question);
    }

    @Override
    public void enterRoot(QLParser.RootContext ctx) {
//        super.enterRoot(ctx);
        System.out.println("root entered");
    }

    @Override
    public void exitRoot(QLParser.RootContext ctx) {
        System.out.println("root exited");
        System.out.println(QuestionDatabase.getInstance().toString());
    }

    @Override
    public void enterQuestionString(QLParser.QuestionStringContext ctx) {
        QuestionDatabase.getInstance().addQuestionString(ctx.STRING().toString());
    }

    @Override
    public void enterQuestionType(QLParser.QuestionTypeContext ctx) {
        QuestionDatabase.getInstance().addQuestionType(ctx.type().toString());
    }

    @Override
    public void enterIdentifier(QLParser.IdentifierContext ctx) {
        QuestionDatabase.getInstance().addQuestionName(ctx.IDENTIFIER().toString());
    }

    @Override
    public void enterCondition(QLParser.ConditionContext ctx) {
        Condition condition = new Condition();

        // TODO build condition

        QuestionDatabase.getInstance().addCondition(condition);
    }

    @Override
    public void enterExpression(QLParser.ExpressionContext ctx) {
//        Expression expression = ;
    }

    @Override
    public void exitCondition(QLParser.ConditionContext ctx) {
        QuestionDatabase.getInstance().removeCondition();
    }
}
