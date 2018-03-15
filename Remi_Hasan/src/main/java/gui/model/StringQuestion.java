package gui.model;

import gui.widgets.StringWidget;
import gui.widgets.Widget;
import ql.model.expression.Expression;

public class StringQuestion extends BaseQuestion {

    public StringQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Widget getWidget() {
        StringWidget widget = new StringWidget(this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
