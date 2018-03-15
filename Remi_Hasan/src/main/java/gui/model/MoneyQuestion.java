package gui.model;

import gui.widgets.DoubleWidget;
import gui.widgets.MoneyWidget;
import gui.widgets.Widget;
import ql.model.expression.Expression;

public class MoneyQuestion extends BaseQuestion {

    public MoneyQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Widget getWidget() {
        MoneyWidget widget = new MoneyWidget(this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
