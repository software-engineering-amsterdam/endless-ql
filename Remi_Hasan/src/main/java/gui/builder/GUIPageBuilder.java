package gui.builder;

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

        // Collect defaultStyles for this page TODO: separate class?
        List<DefaultStyle> defaultStyles = new ArrayList<>();
        page.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(DefaultStyle defaultStyle) {
                defaultStyles.add(defaultStyle);
                return null;
            }
        });

        // Add all questions and sections on this page
        GUIElementBuilder guiElementBuilder = new GUIElementBuilder(this.guiQuestionMap, defaultStyles);
        for (Statement statement : page.getStatements()) {
            guiElements.addAll(guiElementBuilder.visit(statement));
        }

        return new GUIPage(page.getIdentifier(), guiElements);
    }
}
