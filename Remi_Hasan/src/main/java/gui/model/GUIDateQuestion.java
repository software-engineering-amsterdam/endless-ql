package gui.model;

import gui.widgets.DateWidget;
import gui.widgets.IntegerWidget;
import ql.model.expression.Expression;

import java.time.LocalDate;

public class GUIDateQuestion extends GUIQuestion<LocalDate> {

    public GUIDateQuestion(String name, String label, Expression condition, boolean isComputed) {
        super(name, label, condition, isComputed);

        DateWidget widget = new DateWidget(label);

        widget.setListener((observable, oldValue, newValue) -> {
            this.setValue(newValue);
        });

        this.widget = widget;
    }

}
