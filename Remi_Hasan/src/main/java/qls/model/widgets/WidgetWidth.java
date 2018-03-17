package qls.model.widgets;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.QLSNode;

public class WidgetWidth extends Widget {
    public final int width;

    public WidgetWidth(Token token, int width) {
        super(token, WidgetType.TEXT);
        this.width = width;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
