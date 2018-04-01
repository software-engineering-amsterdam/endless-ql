package qls.model.widget;

import qls.model.QLSNode;

public abstract class Widget extends QLSNode {
    private final WidgetType type;

    Widget(WidgetType type) {
        this.type = type;
    }

    public WidgetType getType() {
        return type;
    }
}