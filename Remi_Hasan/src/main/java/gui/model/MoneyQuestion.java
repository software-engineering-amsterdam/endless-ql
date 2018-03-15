package gui.model;

import gui.widgets.DoubleWidget;
import gui.widgets.MoneyWidget;
import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.model.expression.Expression;

public class MoneyQuestion extends BaseQuestion {

    public MoneyQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        super(name, label, condition, isComputed, listener);
    }

    @Override
    public Widget getWidget() {
        MoneyWidget widget = new MoneyWidget(listener, this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
