package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class TextBoxWidget extends Widget {
    public TextBoxWidget() {
        super(WidgetType.TEXTBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
