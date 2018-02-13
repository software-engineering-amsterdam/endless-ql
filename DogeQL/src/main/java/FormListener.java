import java.util.ArrayList;
import java.util.Collection;

public class FormListener extends QuestionareLanguageParserBaseListener {
    private Form form;
    private Collection<Question> questions;

    @Override
    public void enterForm(QuestionareLanguageParser.FormContext ctx){
        questions = new ArrayList<>();
    }

    @Override
    public void exitForm(QuestionareLanguageParser.FormContext ctx) {
        String identefier = ctx.IDENTIFIER().getText();

        form = new Form(identefier, questions);
    }

    @Override
    public void exitQuestion(QuestionareLanguageParser.QuestionContext ctx) {
        String question = ctx.STRING_LITERAL().getText();
        String type = ctx.TYPE().getText();
        String identifier = ctx.IDENTIFIER().getText();

        questions.add(new Question(question, identifier, type));
    }
    public Form getForm(){
        return form;
    }
}
