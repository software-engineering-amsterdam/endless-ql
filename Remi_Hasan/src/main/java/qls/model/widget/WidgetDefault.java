package qls.model.widget;

import qls.IQLSVisitor;

public class WidgetDefault extends Widget {
    public WidgetDefault() {
        super(WidgetType.DEFAULT);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
