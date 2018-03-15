package gui.model;

import gui.widgets.IntegerWidget;
import ql.model.expression.Expression;

public class GUIIntegerQuestion extends GUIQuestion<String> {

    public GUIIntegerQuestion(String name, String label, Expression condition, boolean isComputed) {
        super(name, label, condition, isComputed);

        IntegerWidget widget = new IntegerWidget(label);

        widget.setListener((observable, oldValue, newValue) -> {
            this.setValue(newValue);
        });

        this.widget = widget;
    }

}
