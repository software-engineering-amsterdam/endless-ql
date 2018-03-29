package qls.model.widget;

import qls.IQLSVisitor;

public class WidgetCheckBox extends Widget {

    public WidgetCheckBox() {
        super(WidgetType.CHECKBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
