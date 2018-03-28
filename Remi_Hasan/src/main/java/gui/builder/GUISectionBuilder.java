package gui.builder;

import gui.model.GUIQuestion;
import gui.model.GUISection;
import gui.model.GUIElement;
import qls.QLSVisitor;
import qls.model.statement.DefaultStyle;
import qls.model.statement.Section;
import qls.model.statement.Statement;

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
        List<DefaultStyle> sectionDefaultStyles = defaultStyles;

        // Collect defaultStyles for this section
        section.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(DefaultStyle defaultStyle) {
                sectionDefaultStyles.add(defaultStyle);
                return null;
            }
        });

        // Get all questions and nested sections
        GUIElementBuilder guiElementBuilder = new GUIElementBuilder(this.guiQuestionMap, sectionDefaultStyles);
        for (Statement statement : section.getStatements()) {
            guiElements.addAll(guiElementBuilder.visit(statement));
        }

        return new GUISection(section.getTitle(), guiElements);
    }
}
