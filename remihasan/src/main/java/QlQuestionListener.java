public class QlQuestionListener extends QLBaseListener {
    @Override
    public void enterQuestion(QLParser.QuestionContext ctx) {
        System.out.println("QUESTION:");
        System.out.println(ctx.questionString().getText());

        System.out.print("PARSING QUESTION...");



        try{
            String questionType = ctx.questionType().getText();

            String name = ctx.identifier().toString();
            String text = ctx.questionString().toString();
            Answer answer = AnswerFactory.createAnswer(questionType);
            Question question = new Question(name, text, answer);
            QuestionDatabase.getInstance().addQuestion(question);
        } catch(UnknownQuestionTypeException e){
            // TODO: decide what to do in case we cannot parse a question, for example when an unknown type is seen
            e.printStackTrace();
            System.exit(0);
        }
    }
}
