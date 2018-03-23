package gui;

import gui.model.*;
import gui.widgets.GUIWidget;
import gui.widgets.WidgetFactory;
import ql.model.Form;
import ql.model.Question;
import qls.model.Page;
import qls.model.Section;
import qls.model.StyleSheet;

import java.util.ArrayList;
import java.util.List;

public class GUIFormBuilder {

    public static GUIForm buildQLForm(Form form){
        List<GUIInterface> guiLabelWithWidgets = new ArrayList<>();

        for(Question question : form.questions) {
            GUILabelWithWidget guiLabelWithWidget = buildQuestion(question);
            guiLabelWithWidgets.add(guiLabelWithWidget);
        }

        return new GUIForm(form.identifier, guiLabelWithWidgets);
    }

    public static GUIForm buildQLSForm(Form form, StyleSheet qlsStyleSheet){
        List<GUIInterface> guiPages = new ArrayList<>();
        for(Page qlsPage : qlsStyleSheet.getPages()){
            for(Section qlsSection : qlsPage.getSections()){
                GUISection guiSection = buildSection(form, qlsSection);
                guiPages.add(guiSection);
            }
        }
        return new GUIForm(form.identifier, guiPages);
    }

    private static GUISection buildSection(Form form, Section qlsSection) {
        List<GUIInterface> guiChildren = new ArrayList<>();
        for(qls.model.Question qlsQuestion : qlsSection.getQuestions()){
            Question question = form.questions.stream()
                    .filter(x -> x.identifier.equals(qlsQuestion.name))
                    .findFirst()
                    .get();
            GUILabelWithWidget guiLabelWithWidget = buildQuestion(question);
            guiChildren.add(guiLabelWithWidget);
        }
        for(Section glsSubSection : qlsSection.getSections()){
            GUISection guiSection = buildSection(form, glsSubSection);
            guiChildren.add(guiSection);
        }
        return new GUISection(qlsSection.identifier, guiChildren);
    }

    public static GUILabelWithWidget buildQuestion(Question question){
        GUILabel label = new GUILabel(question.label);
        GUIWidget widget = WidgetFactory.getDefaultWidget(question.identifier, question.isComputed(), question.type);
        return new GUILabelWithWidget(question.identifier, question.isComputed(), label, widget);
    }
}
