package qls.model;

import org.antlr.v4.runtime.Token;
import ql.model.expression.ReturnType;
import qls.IQLSVisitor;
import qls.model.widgets.Widget;

import java.util.List;

public class DefaultStyle extends QLSNode {

    public final ReturnType type;
    private final List<Widget> widgets;

    public DefaultStyle(Token token, ReturnType type, List<Widget> widgets) {
        super(token);
        this.type = type;
        this.widgets = widgets;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
