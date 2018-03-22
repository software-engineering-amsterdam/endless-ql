package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.style.StyleAttribute;
import qls.model.widget.Widget;

import java.util.List;

public class Question extends QLSNode {

    public final String name;
    public final Widget widget;
    public final List<StyleAttribute> styleAttributes;

    public Question(Token token, String name, Widget widget, List<StyleAttribute> styleAttributes) {
        super(token);
        this.name = name;
        this.widget = widget;
        this.styleAttributes = styleAttributes;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
