package gui.model;

import gui.widgets.CheckboxWidget;
import gui.widgets.StringWidget;
import gui.widgets.Widget;
import ql.model.expression.Expression;

public class BooleanQuestion extends BaseQuestion {

    public BooleanQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Widget getWidget() {
        // TODO: widget type based on QLS
        CheckboxWidget widget = new CheckboxWidget(this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
