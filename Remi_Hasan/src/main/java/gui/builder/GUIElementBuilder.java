package gui.builder;

import gui.model.GUIElement;
import gui.model.GUIQuestion;
import gui.model.GUIQuestionWithStyling;
import ql.model.expression.ReturnType;
import qls.model.statement.DefaultStyle;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.model.widget.DefaultWidget;
import qls.model.widget.Widget;
import qls.visitor.QLSVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIElementBuilder extends QLSVisitor<List<GUIElement>> {
    private final Map<String, List<GUIQuestion>> guiQuestionMap;
    private final List<DefaultStyle> defaultStyles;

    GUIElementBuilder(Map<String, List<GUIQuestion>> guiQuestionMap, List<DefaultStyle> defaultStyles) {
        this.guiQuestionMap = guiQuestionMap;
        this.defaultStyles = defaultStyles;
    }

    @Override
    public List<GUIElement> visit(Section section) {
        GUISectionBuilder guiSectionBuilder = new GUISectionBuilder(guiQuestionMap, defaultStyles);
        return List.of(guiSectionBuilder.visit(section));
    }

    @Override
    public List<GUIElement> visit(QuestionReference questionReference) {
        List<GUIElement> guiElements = new ArrayList<>();

        // Add all QL questions with this identifier here, inside decorator for the styling
        for (GUIQuestion guiQuestion : guiQuestionMap.get(questionReference.getIdentifier())) {
            // Get widget defined for question itself, or from page/section default styles
            Widget widget;
            if (questionReference.getWidget() != null) {
                widget = questionReference.getWidget();
            } else {
                widget = this.getWidgetFromStyles(defaultStyles, guiQuestion.getType());
            }

            guiElements.add(new GUIQuestionWithStyling(guiQuestion, this.defaultStyles, widget));
        }

        return guiElements;
    }

    @Override
    public List<GUIElement> visit(DefaultStyle defaultStyle) {
        // We do not render this, instead we already collected them and pass them through to the questions
        // to be rendered
        return new ArrayList<>();
    }

    private Widget getWidgetFromStyles(List<DefaultStyle> defaultStyles, ReturnType questionType) {
        Widget widget = new DefaultWidget();

        // Go through default styles and find if a widget type has been set for this question type
        for (DefaultStyle defaultStyle : defaultStyles) {
            if (defaultStyle.getType() == questionType && defaultStyle.getWidget() != null) {
                widget = defaultStyle.getWidget();
            }
        }

        return widget;
    }
}
