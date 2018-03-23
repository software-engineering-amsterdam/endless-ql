package gui.builder;

import gui.builder.GUIQuestionsBuilder;
import gui.model.*;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import ql.model.Form;
import ql.model.Question;
import ql.model.Statement;
import ql.model.expression.variable.ExpressionVariableBoolean;
import qls.model.Page;
import qls.model.Section;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;

public class GUIFormBuilder {

    public static GUIForm buildQLForm(Form form) {
        // To render, we do not need structures like the if/else block,
        // we only want to get a form with questions.
        // Therefore, create a simpler GUI model of the form, containing questions
        // which contain the if conditions
        List<GUIInterface> guiQuestions = new ArrayList<>();

        // Get all form questions from the QL form
        // and move all conditions from if blocks to the GUIQuestion objects
        GUIQuestionsBuilder guiQuestionsBuilder = new GUIQuestionsBuilder(new ExpressionVariableBoolean(null, true));
        for (Statement statement : form.getStatements()) {
            List<GUILabelWithWidget> guiQuestionList = statement.accept(guiQuestionsBuilder);
            guiQuestions.addAll(guiQuestionList);
        }

        return new GUIForm(form.identifier, guiQuestions);
    }

//    public static GUIForm buildQLSForm(Form form, StyleSheet qlsStyleSheet) {
//        List<GUIInterface> guiPages = new ArrayList<>();
//        for (Page qlsPage : qlsStyleSheet.getPages()) {
//            GUIPage guiPage = buildQLSPage(form, qlsPage);
//            guiPages.add(guiPage);
//        }
//        return new GUIForm(form.identifier, guiPages);
//    }
//
//    private static GUIPage buildQLSPage(Form form, Page qlsPage) {
//        List<GUIInterface> guiSections = new ArrayList<>();
//        for (Section qlsSection : qlsPage.getSections()) {
//            GUISection guiSection = buildQLSSection(form, qlsSection);
//            guiSections.add(guiSection);
//        }
//        return new GUIPage(qlsPage.identifier, guiSections);
//    }

//    private static GUISection buildQLSSection(Form form, Section qlsSection) {
//        List<GUIInterface> guiChildren = new ArrayList<>();
//        for (qls.model.Question qlsQuestion : qlsSection.getQuestions()) {
//            Question question = form.questions.stream()
//                    .filter(x -> x.identifier.equals(qlsQuestion.name))
//                    .findFirst()
//                    .get();
//            GUILabelWithWidget guiLabelWithWidget = buildQuestion(question);
//            guiChildren.add(guiLabelWithWidget);
//        }
//        for (Section glsSubSection : qlsSection.getSections()) {
//            GUISection guiSection = buildQLSSection(form, glsSubSection);
//            guiChildren.add(guiSection);
//        }
//        return new GUISection(qlsSection.identifier, guiChildren);
//    }
}
