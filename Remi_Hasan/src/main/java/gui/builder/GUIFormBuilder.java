package gui.builder;

import gui.model.GUIForm;
import gui.model.GUIFormWithStyling;
import gui.model.GUIPage;
import gui.model.GUIQuestion;
import ql.model.Form;
import ql.model.statement.Statement;
import qls.model.Page;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIFormBuilder {

    public GUIForm buildQLForm(Form form) {
        return new GUIForm(form.getIdentifier(), this.getGUIQuestions(form));
    }

    public GUIFormWithStyling buildQLSForm(Form form, StyleSheet styleSheet) {
        // Translate all form Questions to GUIQuestions
        List<GUIQuestion> guiQuestions = getGUIQuestions(form);

        // Map all questions with the same identifier
        Map<String, List<GUIQuestion>> guiQuestionMap = this.getGUIQuestionsByIdentifier(guiQuestions);

        // Build GUIPage objects
        List<GUIPage> pages = new ArrayList<>();
        GUIPageBuilder guiPageBuilder = new GUIPageBuilder(guiQuestionMap);
        for (Page page : styleSheet.getPages()) {
            pages.add(guiPageBuilder.visit(page));
        }

        return new GUIFormWithStyling(styleSheet.getIdentifier(), pages);
    }

    private List<GUIQuestion> getGUIQuestions(Form form) {
        // To render, we do not need structures like the if/else block,
        // we only want to get a form with questions.
        // Therefore, create a simpler GUI model of the form, containing questions
        // which contain the if conditions
        List<GUIQuestion> guiQuestions = new ArrayList<>();

        // Get all form questions from the QL form
        // and move all conditions from if blocks to the GUIQuestion objects
        GUIQuestionsBuilder guiQuestionsBuilder = new GUIQuestionsBuilder();
        for (Statement statement : form.getStatements()) {
            List<GUIQuestion> guiQuestionList = statement.accept(guiQuestionsBuilder);
            guiQuestions.addAll(guiQuestionList);
        }

        return guiQuestions;
    }

    private Map<String, List<GUIQuestion>> getGUIQuestionsByIdentifier(List<GUIQuestion> guiQuestions) {
        // Map all questions with the same identifier
        Map<String, List<GUIQuestion>> guiQuestionMap = new HashMap<>();
        for (GUIQuestion guiQuestion : guiQuestions) {
            if (!(guiQuestionMap.containsKey(guiQuestion.getIdentifier()))) {
                guiQuestionMap.put(guiQuestion.getIdentifier(), new ArrayList<>());
            }

            guiQuestionMap.get(guiQuestion.getIdentifier()).add(guiQuestion);
        }

        return guiQuestionMap;
    }
}