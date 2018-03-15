package gui.model;

import gui.widgets.DateWidget;
import gui.widgets.MoneyWidget;
import gui.widgets.Widget;
import ql.model.expression.Expression;

public class DateQuestion extends BaseQuestion {

    public DateQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Widget getWidget() {
        DateWidget widget = new DateWidget(this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
