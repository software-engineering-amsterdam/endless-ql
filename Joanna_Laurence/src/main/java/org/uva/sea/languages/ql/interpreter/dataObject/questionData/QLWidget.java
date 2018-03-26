package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;

import java.util.List;

public class QLWidget {

    private final WidgetType widgetType;
    private final List<String> parameters;

    public QLWidget(final WidgetType widgetType, final List<String> parameters) {
        this.widgetType = widgetType;
        this.parameters = parameters;
    }

    public final List<String> getParameters() {
        return this.parameters;
    }

    public final WidgetType getWidgetType() {
        return this.widgetType;
    }

    @Override
    public final String toString() {
        return "Widget{" +
                "widgetType=" + this.widgetType +
                ", parameters=" + this.parameters +
                '}';
    }
}
