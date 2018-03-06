package qlviz.model.style;

import java.util.Optional;

public class Question {

    private final String name;
    private final WidgetType widgetType;

    public Question(String name, WidgetType widgetType) {
        this.name = name;
        this.widgetType = widgetType;
    }

    public Question(String name) {
        this.name = name;
        this.widgetType = null;
    }

    public String getName() {
        return name;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }
}

