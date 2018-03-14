package gui.model;

import gui.formfx.control.Input;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import ql.model.expression.Expression;

public class StringQuestion extends BaseQuestion {
    public StringQuestion(String label, Expression condition, boolean isComputed) {
        super(label, condition, isComputed);
    }

    @Override
    public Control render() {
        TextInputControl textField = Input.textField();
        textField.setEditable(!this.isComputed);
        return textField;
    }
}
