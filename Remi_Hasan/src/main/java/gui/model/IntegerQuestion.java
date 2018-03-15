package gui.model;

import gui.widgets.IntegerWidget;
import gui.widgets.StringWidget;
import gui.widgets.Widget;
import ql.model.expression.Expression;

public class IntegerQuestion extends BaseQuestion {

    public IntegerQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Widget getWidget() {
        IntegerWidget widget = new IntegerWidget(this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
