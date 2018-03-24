package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;
import qls.model.widget.Widget;

public class QuestionReference extends Statement {

    public final String name;
    private final Widget widget;

    public QuestionReference(Token token, String name, Widget widget) {
        super(token);
        this.name = name;
        this.widget = widget;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
