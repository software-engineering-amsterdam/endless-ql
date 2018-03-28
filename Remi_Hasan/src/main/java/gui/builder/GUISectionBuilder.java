package gui.builder;

import gui.model.GUIQuestion;
import gui.model.GUISection;
import gui.model.GUISectionElement;
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
        List<GUISectionElement> guiSectionElements = new ArrayList<>();
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
        GUISectionElementBuilder guiSectionElementBuilder = new GUISectionElementBuilder(this.guiQuestionMap, sectionDefaultStyles);
        for (Statement statement : section.getStatements()) {
            guiSectionElements.addAll(guiSectionElementBuilder.visit(statement));
        }

        return new GUISection(section.getTitle(), guiSectionElements);
    }
}
