package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class DropDownWidget extends Widget {

    private final String trueLabel;
    private final String falseLabel;

    public DropDownWidget(String trueLabel, String falseLabel) {
        super(WidgetType.DROPDOWN);
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    public String getTrueLabel() {
        return trueLabel;
    }

    public String getFalseLabel() {
        return falseLabel;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
