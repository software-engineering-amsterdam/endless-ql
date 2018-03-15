package gui.model;

import gui.widgets.MoneyWidget;
import gui.widgets.StringWidget;
import ql.model.expression.Expression;

public class GUIStringQuestion extends GUIQuestion<String> {

    public GUIStringQuestion(String name, String label, Expression condition, boolean isComputed) {
        super(name, label, condition, isComputed);

        StringWidget widget = new StringWidget(label);

        widget.setListener((observable, oldValue, newValue) -> {
            this.setValue(newValue);
        });

        this.widget = widget;
    }
}
