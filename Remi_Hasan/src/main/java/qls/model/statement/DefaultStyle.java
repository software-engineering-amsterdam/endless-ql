package qls.model.statement;

import ql.model.expression.ReturnType;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;
import qls.visitor.IQLSVisitor;

import java.util.List;

public class DefaultStyle extends Statement {

    private final ReturnType type;
    private final List<StyleAttribute> styleAttributes;
    private final Widget widget;

    public DefaultStyle(ReturnType type, List<StyleAttribute> styleAttributes, Widget widget) {
        this.type = type;
        this.styleAttributes = styleAttributes;
        this.widget = widget;
    }

    public ReturnType getType() {
        return type;
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
