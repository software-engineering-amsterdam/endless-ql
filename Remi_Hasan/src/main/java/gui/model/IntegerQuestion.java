package gui.model;

import gui.widgets.IntegerWidget;
import gui.widgets.StringWidget;
import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.model.expression.Expression;

public class IntegerQuestion extends BaseQuestion {

    public IntegerQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        super(name, label, condition, isComputed, listener);
    }

    @Override
    public Widget getWidget() {
        IntegerWidget widget = new IntegerWidget(listener, this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
