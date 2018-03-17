package qls.model.widgets;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.QLSNode;

public class WidgetColor extends Widget {
    private final String hexColor;

    public WidgetColor(Token token, String hexColor) {
        super(token, WidgetType.TEXT);
        this.hexColor = hexColor;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
