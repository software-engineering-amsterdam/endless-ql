package qls.model.style;

import gui.widgets.WidgetInterface;
import org.antlr.v4.runtime.Token;
import qls.model.QLSNode;

public abstract class StyleAttribute extends QLSNode {

    StyleAttribute(Token start) {
        super(start);
    }

    public abstract void apply(WidgetInterface widget);
}
