package qls.model;

import org.antlr.v4.runtime.Token;
import ql.model.expression.ReturnType;
import qls.IQLSVisitor;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;

import java.util.List;

public class DefaultStyle extends Statement {

    public final ReturnType type;
    private final List<StyleAttribute> styleAttributes;
    private final Widget widget;

    public DefaultStyle(Token token, ReturnType type, List<StyleAttribute> styleAttributes, Widget widget) {
        super(token);
        this.type = type;
        this.styleAttributes = styleAttributes;
        this.widget = widget;
    }

    public List<StyleAttribute> getStyleAttributes() {
        return styleAttributes;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
