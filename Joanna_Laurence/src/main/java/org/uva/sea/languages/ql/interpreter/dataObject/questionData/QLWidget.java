package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;

import java.util.List;

public class QLWidget {

    WidgetType widgetType;
    List<String> parameters;

    public QLWidget(WidgetType widgetType, List<String> parameters) {
        this.widgetType = widgetType;
        this.parameters = parameters;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    @Override
    public String toString() {
        return "QLWidget{" +
                "parameters=" + parameters +
                '}';
    }
}
