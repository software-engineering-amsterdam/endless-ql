package org.uva.sea.languages.qls.interpreter.widget;

import java.util.List;

public class QLSWidget {

    private final WidgetType widgetType;
    private final List<String> parameters;

    public QLSWidget(WidgetType widgetType, List<String> parameters) {
        this.widgetType = widgetType;
        this.parameters = parameters;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public WidgetType getWidgetType() {
        return this.widgetType;
    }

    @Override
    public String toString() {
        return "Widget{" +
                "widgetType=" + this.widgetType +
                ", parameters=" + this.parameters +
                '}';
    }
}
