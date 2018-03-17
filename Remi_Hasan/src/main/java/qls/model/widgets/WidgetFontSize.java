package qls.model.widgets;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.QLSNode;

public class WidgetFontSize extends Widget {
    private final int fontSize;

    public WidgetFontSize(Token token, int fontSize) {
        super(token, WidgetType.TEXT);
        this.fontSize = fontSize;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
