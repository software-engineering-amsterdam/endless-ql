package gui.model;

import gui.widgets.DecimalWidget;
import gui.widgets.MoneyWidget;
import ql.model.expression.Expression;

public class GUIMoneyQuestion extends GUIQuestion<String> {

    public GUIMoneyQuestion(String name, String label, Expression condition, boolean isComputed) {
        super(name, label, condition, isComputed);

        MoneyWidget widget = new MoneyWidget(label);

        widget.setListener((observable, oldValue, newValue) -> {
            this.setValue(newValue);
        });

        this.widget = widget;
    }

}
