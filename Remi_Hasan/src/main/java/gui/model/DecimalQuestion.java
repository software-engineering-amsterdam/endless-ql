package gui.model;

import gui.widgets.DoubleWidget;
import gui.widgets.IntegerWidget;
import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.model.expression.Expression;

public class DecimalQuestion extends BaseQuestion {

    public DecimalQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        super(name, label, condition, isComputed, listener);
    }

    @Override
    public Widget getWidget() {
        DoubleWidget widget = new DoubleWidget(listener, this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
