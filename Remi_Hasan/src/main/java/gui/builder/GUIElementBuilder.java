package gui.builder;

import gui.model.GUIQuestion;
import gui.model.GUIQuestionWithStyling;
import gui.model.GUIElement;
import ql.model.expression.ReturnType;
import qls.QLSVisitor;
import qls.model.statement.DefaultStyle;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.model.widget.Widget;
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
        for(GUIQuestion guiQuestion : guiQuestionMap.get(questionReference.getIdentifier())) {
            WidgetType widgetType = getWidgetType(defaultStyles, guiQuestion);
            guiElements.add(new GUIQuestionWithStyling(guiQuestion, this.defaultStyles, widgetType));
        }
        return guiElements;
    }



    private WidgetType getWidgetType(List<DefaultStyle> defaultStyles, GUIQuestion guiQuestion){
        // If a question has a widget type, don't use other widget types of the default styles
        if(guiQuestion.getWidgetType() != WidgetType.DEFAULT){
            return guiQuestion.getWidgetType();
        }

        WidgetType widgetType = WidgetType.DEFAULT;
        for(DefaultStyle defaultStyle : defaultStyles){
            ReturnType defaultStyleType = defaultStyle.getType();
            ReturnType questionType = guiQuestion.getType();
            Widget widget = defaultStyle.getWidget();
            WidgetType defaultStyleWidgetType = widget.getType();
            if(defaultStyleType.equals(questionType) && defaultStyleWidgetType != WidgetType.DEFAULT){
                widgetType = defaultStyleWidgetType;
            }
        }
        return widgetType;
    }

    @Override
    public List<GUIElement> visit(DefaultStyle defaultStyle) {
        // We do not render this, instead we already collected them and pass them through to the questions
        // to be rendered
        return new ArrayList<>();
    }
}
