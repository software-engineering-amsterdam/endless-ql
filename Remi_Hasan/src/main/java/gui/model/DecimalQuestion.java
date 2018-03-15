package gui.model;

import gui.widgets.DoubleWidget;
import gui.widgets.IntegerWidget;
import gui.widgets.Widget;
import ql.model.expression.Expression;

public class DecimalQuestion extends BaseQuestion {

    public DecimalQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Widget getWidget() {
        DoubleWidget widget = new DoubleWidget(this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
