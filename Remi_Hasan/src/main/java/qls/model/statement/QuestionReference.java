package qls.model.statement;

import qls.IQLSVisitor;
import qls.model.widget.Widget;

public class QuestionReference extends Statement {

    private final String identifier;
    private final Widget widget;

    public QuestionReference(String identifier, Widget widget) {
        this.identifier = identifier;
        this.widget = widget;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
