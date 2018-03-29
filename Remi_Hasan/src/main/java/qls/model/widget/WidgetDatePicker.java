package qls.model.widget;

import qls.IQLSVisitor;

public class WidgetDatePicker extends Widget {

    WidgetDatePicker() {
        super(WidgetType.DATE);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
