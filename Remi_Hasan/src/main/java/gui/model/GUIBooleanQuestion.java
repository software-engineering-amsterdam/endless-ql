package gui.model;

import gui.widgets.CheckboxWidget;
import gui.widgets.MoneyWidget;
import ql.evaluation.IExpressionVisitor;
import ql.model.expression.Expression;

public class GUIBooleanQuestion extends GUIQuestion<Boolean> {

    public GUIBooleanQuestion(String name, String label, Expression condition, boolean isComputed) {
        super(name, label, condition, isComputed);

        CheckboxWidget widget = new CheckboxWidget(label);

        widget.setListener((observable, oldValue, newValue) -> this.setValue(newValue));

        this.widget = widget;
    }

}
