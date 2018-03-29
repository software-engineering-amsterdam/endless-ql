package qls.model.widget;

import qls.IQLSVisitor;

public class WidgetSpinBox extends Widget {
    public WidgetSpinBox() {
        super(WidgetType.SPINBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return null;
    }
}
