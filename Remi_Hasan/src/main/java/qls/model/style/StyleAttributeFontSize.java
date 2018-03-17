package qls.model.style;

import gui.widgets.WidgetInterface;
import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class StyleAttributeFontSize extends StyleAttribute {
    private final int fontSize;

    public StyleAttributeFontSize(Token start, int fontSize) {
        super(start);
        this.fontSize = fontSize;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void apply(WidgetInterface widget) {
        widget.setFontSize(fontSize);
    }
}
