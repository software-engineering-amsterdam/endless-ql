package gui.builder;

import gui.model.GUIPage;
import gui.model.GUIQuestion;
import gui.model.GUISectionElement;
import qls.QLSVisitor;
import qls.model.statement.DefaultStyle;
import qls.model.Page;
import qls.model.statement.Statement;

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
        List<GUISectionElement> guiElements = new ArrayList<>();

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
        GUISectionElementBuilder guiSectionElementBuilder = new GUISectionElementBuilder(guiQuestionMap, defaultStyles);
        for(Statement statement : page.getStatements()) {
            guiElements.addAll(guiSectionElementBuilder.visit(statement));
        }

        return new GUIPage(page.getIdentifier(), guiElements);
    }
}
