package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class DefaultWidget extends Widget {
    public DefaultWidget() {
        super(WidgetType.DEFAULT);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
