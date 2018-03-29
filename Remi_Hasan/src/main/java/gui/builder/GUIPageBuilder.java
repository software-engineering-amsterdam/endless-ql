package gui.builder;

import gui.builder.helper.DefaultStylesCollector;
import gui.model.GUIElement;
import gui.model.GUIPage;
import gui.model.GUIQuestion;
import qls.model.Page;
import qls.model.statement.DefaultStyle;
import qls.model.statement.Statement;
import qls.visitor.QLSVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIPageBuilder extends QLSVisitor<GUIPage> {

    private final Map<String, List<GUIQuestion>> guiQuestionMap;

    GUIPageBuilder(Map<String, List<GUIQuestion>> guiQuestionMap) {
        this.guiQuestionMap = guiQuestionMap;
    }

    @Override
    public GUIPage visit(Page page) {
        List<GUIElement> guiElements = new ArrayList<>();
        List<DefaultStyle> defaultStyles = DefaultStylesCollector.collect(page, new ArrayList<>());

        // Add all questions and sections on this page
        GUIElementBuilder guiElementBuilder = new GUIElementBuilder(this.guiQuestionMap, defaultStyles);
        for (Statement statement : page.getStatements()) {
            guiElements.addAll(guiElementBuilder.visit(statement));
        }

        return new GUIPage(page.getIdentifier(), guiElements);
    }
}
