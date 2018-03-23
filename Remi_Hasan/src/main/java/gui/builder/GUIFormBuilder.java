package gui.builder;

import gui.model.GUIForm;
import gui.model.GUIQuestion;
import gui.builder.GUIQuestionsBuilder;
import ql.model.Form;
import ql.model.Statement;
import ql.model.expression.variable.ExpressionVariableBoolean;

import java.util.ArrayList;
import java.util.List;

public class GUIFormBuilder {

    public static GUIForm build(Form form) {
        // To render, we do not need structures like the if/else block,
        // we only want to get a form with questions.
        // Therefore, create a simpler GUI model of the form, containing questions
        // which contain the if conditions
        List<GUIQuestion> guiQuestions = new ArrayList<>();

        // Get all form questions from the QL form
        // and move all conditions from if blocks to the GUIQuestion objects
        GUIQuestionsBuilder guiQuestionsBuilder = new GUIQuestionsBuilder(new ExpressionVariableBoolean(null, true));
        for (Statement statement : form.getStatements()) {
            List<GUIQuestion> guiQuestionList = statement.accept(guiQuestionsBuilder);
            guiQuestions.addAll(guiQuestionList);
        }

        return new GUIForm(form.identifier, guiQuestions);
    }
}
