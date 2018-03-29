package gui.builder;

import gui.model.GUIElement;
import gui.model.GUIQuestion;
import gui.model.GUIQuestionWithStyling;
import ql.model.expression.ReturnType;
import qls.QLSVisitor;
import qls.model.statement.DefaultStyle;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.model.widget.Widget;
import qls.model.widget.WidgetDefault;
import qls.model.widget.WidgetType;

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
            Widget widget;
            // TODO: default instead of null??
            if (questionReference.getWidget() != null) {
                widget = questionReference.getWidget();
            } else {
                widget = this.getWidget(defaultStyles, guiQuestion);
            }
            guiElements.add(new GUIQuestionWithStyling(guiQuestion, this.defaultStyles, widget));
        }
        return guiElements;
    }

    private Widget getWidget(List<DefaultStyle> defaultStyles, GUIQuestion guiQuestion) {
        // If a question has a widget type, don't use other widget types of the default styles
        if (guiQuestion.getWidget().getType() != WidgetType.DEFAULT) {
            return guiQuestion.getWidget();
        }

        // TODO: better
        Widget widget = new WidgetDefault(null, WidgetType.DEFAULT);
        for (DefaultStyle defaultStyle : defaultStyles) {
            if(defaultStyle.getType() == guiQuestion.getType()) {
                widget = defaultStyle.getWidget();
            }
        }
        return widget;
    }

    @Override
    public List<GUIElement> visit(DefaultStyle defaultStyle) {
        // We do not render this, instead we already collected them and pass them through to the questions
        // to be rendered
        return new ArrayList<>();
    }
}
