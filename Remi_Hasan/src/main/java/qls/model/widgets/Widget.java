package qls.model.widgets;

import org.antlr.v4.runtime.Token;
import qls.model.QLSNode;

public abstract class Widget extends QLSNode {

    public final WidgetType type;

    Widget(Token start, WidgetType type) {
        super(start);
        this.type = type;
    }
}