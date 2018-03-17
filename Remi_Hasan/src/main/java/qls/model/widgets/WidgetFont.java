package qls.model.widgets;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.QLSNode;

// TODO: not a subclass of widget
public class WidgetFont extends Widget {
    private final String fontFamily;

    public WidgetFont(Token token, String fontFamily) {
        super(token, WidgetType.TEXT);
        this.fontFamily = fontFamily;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
