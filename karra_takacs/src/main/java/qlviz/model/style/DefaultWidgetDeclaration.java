package qlviz.model.style;

import qlviz.model.question.QuestionType;

import java.util.List;

public class DefaultWidgetDeclaration {

    private final List<PropertySetting> propertySettings;
    private final Widget widget;
    private final QuestionType questionType;

    public DefaultWidgetDeclaration(List<PropertySetting> propertySettings, Widget widget, QuestionType questionType) {
        this.propertySettings = propertySettings;
        this.widget = widget;
        this.questionType = questionType;
    }

    public List<PropertySetting> getPropertySettings() {
        return propertySettings;
    }

    public Widget getWidget() {
        return widget;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
}
