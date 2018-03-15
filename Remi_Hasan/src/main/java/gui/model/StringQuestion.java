package gui.model;

import gui.widgets.StringWidget;
import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.model.expression.Expression;



public class StringQuestion extends BaseQuestion {

    public StringQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        super(name, label, condition, isComputed, listener);
    }

    @Override
    public Widget getWidget() {
        StringWidget widget = new StringWidget(listener, this.label);
        widget.setDisable(this.isComputed);
        return widget;
    }
}
