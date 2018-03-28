package gui.builder;

import gui.model.GUIQuestion;
import gui.model.GUIQuestionWithStyling;
import gui.model.GUISectionElement;
import qls.QLSVisitor;
import qls.model.DefaultStyle;
import qls.model.QuestionReference;
import qls.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUISectionElementBuilder extends QLSVisitor<List<GUISectionElement>> {
    private final Map<String, List<GUIQuestion>> guiQuestionMap;
    private final List<DefaultStyle> defaultStyles;

    GUISectionElementBuilder(Map<String, List<GUIQuestion>> guiQuestionMap, List<DefaultStyle> defaultStyles) {
        this.guiQuestionMap = guiQuestionMap;
        this.defaultStyles = defaultStyles;
    }

    @Override
    public List<GUISectionElement> visit(Section section) {
        GUISectionBuilder guiSectionBuilder = new GUISectionBuilder(guiQuestionMap, defaultStyles);
        return List.of(guiSectionBuilder.visit(section));
    }

    @Override
    public List<GUISectionElement> visit(QuestionReference questionReference) {
        List<GUISectionElement> guiSectionElements = new ArrayList<>();
        // Add all QL questions with this identifier here, inside decorator for the styling
        for(GUIQuestion guiQuestion : guiQuestionMap.get(questionReference.getIdentifier())) {
            guiSectionElements.add(new GUIQuestionWithStyling(guiQuestion, this.defaultStyles));
        }
        return guiSectionElements;
    }

    @Override
    public List<GUISectionElement> visit(DefaultStyle defaultStyle) {
        // We do not render this, instead we already collected them and pass them through to the questions
        // to be rendered
        return new ArrayList<>();
    }
}
