package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class DatePickerWidget extends Widget {

    DatePickerWidget() {
        super(WidgetType.DATE);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
