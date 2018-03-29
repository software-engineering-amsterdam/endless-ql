package qls.model.widget;

import org.antlr.v4.runtime.Token;
import qls.model.QLSNode;

public abstract class Widget extends QLSNode {
    private final WidgetType type;

    Widget(Token start, WidgetType type) {
        super(start);
        this.type = type;
    }

    public WidgetType getType() {
        return type;
    }
}