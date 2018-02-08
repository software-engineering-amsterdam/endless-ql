public class QlQuestionListener extends QLBaseListener {
    @Override
    public void enterQuestion(QLParser.QuestionContext ctx) {
        System.out.println("QUESTION:");
        System.out.println(ctx.questionString().getText());
    }
}
