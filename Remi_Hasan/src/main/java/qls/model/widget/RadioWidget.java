package qls.model.widget;

import qls.visitor.IQLSVisitor;

public class RadioWidget extends Widget {

    private final String trueLabel;
    private final String falseLabel;

    public RadioWidget(String trueLabel, String falseLabel) {
        super(WidgetType.RADIO);
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
