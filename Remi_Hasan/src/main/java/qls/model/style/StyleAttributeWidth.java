package qls.model.style;

import gui.widgets.WidgetInterface;
import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class StyleAttributeWidth extends StyleAttribute {
    private final int width;

    public StyleAttributeWidth(Token start, int width) {
        super(start);
        this.width = width;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void apply(WidgetInterface widget) {
        widget.setWidth(width);
    }
}
