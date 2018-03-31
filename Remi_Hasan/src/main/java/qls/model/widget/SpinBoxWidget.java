package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class SpinBoxWidget extends Widget {
    public SpinBoxWidget() {
        super(WidgetType.SPINBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return null;
    }
}
