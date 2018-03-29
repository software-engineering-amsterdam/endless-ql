package qls.model.widget;

import qls.IQLSVisitor;

public class WidgetTextBox extends Widget {
    public WidgetTextBox() {
        super(WidgetType.TEXTBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
