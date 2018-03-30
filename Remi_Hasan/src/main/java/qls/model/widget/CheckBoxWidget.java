package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class CheckBoxWidget extends Widget {

    public CheckBoxWidget() {
        super(WidgetType.CHECKBOX);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
