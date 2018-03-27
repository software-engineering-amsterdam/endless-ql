package ql.gui.controls;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ql.values.StringValue;
import ql.values.Value;

public class QLTextField extends QLControl {
    private final TextField textField;

    public QLTextField() {
        this.textField = new TextField();
    }

    private StringValue value(String text) {
        return new StringValue(text);
    }

    @Override
    public void setValue(Value value) {
        this.textField.setText((String) value.getValue());
    }

    @Override
    public Pane addControl(GridPane gridPane) {
        gridPane.add(this.textField,1,0);
        return gridPane;
    }
}
