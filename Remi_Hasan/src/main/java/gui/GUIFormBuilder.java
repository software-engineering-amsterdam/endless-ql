package gui;

import gui.model.GUIForm;
import gui.model.GUIQuestion;
import ql.model.Form;
import ql.model.Question;

import java.util.ArrayList;
import java.util.List;

public class GUIFormBuilder {

    public static GUIForm build(Form form) {
        List<GUIQuestion> guiQuestions = new ArrayList<>();

        for(Question question : form.questions) {
            GUIQuestion guiQuestion = new GUIQuestion(question.identifier, question.label, question.type,
                    question.condition, question.isComputed());
            guiQuestions.add(guiQuestion);
        }

        return new GUIForm(form.identifier, guiQuestions);
    }
}
