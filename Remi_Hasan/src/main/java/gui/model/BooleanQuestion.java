package gui.model;

import gui.widgets.CheckboxWidget;
import gui.widgets.StringWidget;
import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.model.expression.Expression;



public class BooleanQuestion extends BaseQuestion {

    public BooleanQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        super(name, label, condition, isComputed, listener);
    }

    @Override
    public Widget getWidget() {
        // TODO: widget type based on QLS
        CheckboxWidget widget = new CheckboxWidget(listener, this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
