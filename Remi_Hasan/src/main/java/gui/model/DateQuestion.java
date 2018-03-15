package gui.model;

import gui.widgets.DateWidget;
import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.model.expression.Expression;

public class DateQuestion extends BaseQuestion {

    public DateQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        super(name, label, condition, isComputed, listener);
    }

    @Override
    public Widget getWidget() {
        DateWidget widget = new DateWidget(listener, this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
