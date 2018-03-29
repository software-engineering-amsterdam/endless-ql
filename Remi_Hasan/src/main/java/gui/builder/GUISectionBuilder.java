package gui.builder;

import gui.builder.helper.DefaultStylesCollector;
import gui.model.GUIElement;
import gui.model.GUIQuestion;
import gui.model.GUISection;
import qls.model.statement.DefaultStyle;
import qls.model.statement.Section;
import qls.model.statement.Statement;
import qls.visitor.QLSVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUISectionBuilder extends QLSVisitor<GUISection> {

    private final Map<String, List<GUIQuestion>> guiQuestionMap;
    private final List<DefaultStyle> defaultStyles;

    GUISectionBuilder(Map<String, List<GUIQuestion>> guiQuestionMap, List<DefaultStyle> defaultStyles) {
        this.guiQuestionMap = guiQuestionMap;
        this.defaultStyles = defaultStyles;
    }

    @Override
    public GUISection visit(Section section) {
        List<GUIElement> guiElements = new ArrayList<>();
        List<DefaultStyle> sectionDefaultStyles = DefaultStylesCollector.collect(section, this.defaultStyles);

        // Get all questions and nested sections
        GUIElementBuilder guiElementBuilder = new GUIElementBuilder(this.guiQuestionMap, sectionDefaultStyles);
        for (Statement statement : section.getStatements()) {
            guiElements.addAll(guiElementBuilder.visit(statement));
        }

        return new GUISection(section.getTitle(), guiElements);
    }
}
