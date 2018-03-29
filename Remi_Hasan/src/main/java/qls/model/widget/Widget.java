package qls.model.widget;

import qls.model.QLSNode;

public abstract class Widget extends QLSNode {
// TODO: not abstract, remove subclasses, just use enum
    private final WidgetType type;

    Widget(WidgetType type) {
        this.type = type;
    }

    public WidgetType getType() {
        return type;
    }
}