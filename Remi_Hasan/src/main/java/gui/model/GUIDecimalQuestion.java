package gui.model;

import gui.widgets.DecimalWidget;
import gui.widgets.IntegerWidget;
import ql.model.expression.Expression;

public class GUIDecimalQuestion extends GUIQuestion<String> {

    public GUIDecimalQuestion(String name, String label, Expression condition, boolean isComputed) {
        super(name, label, condition, isComputed);

        DecimalWidget widget = new DecimalWidget(label);

        widget.setListener((observable, oldValue, newValue) -> {
            this.setValue(newValue);
        });

        this.widget = widget;
    }

}
