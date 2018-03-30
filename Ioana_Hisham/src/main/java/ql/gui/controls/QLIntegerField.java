package ql.gui.controls;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ql.values.IntegerValue;
import ql.values.Value;

public class QLIntegerField extends QLControl {
    private final TextField textField;

    public QLIntegerField() {
        this.textField = new TextField();
    }

    private IntegerValue value(String text) {
        return new IntegerValue(Integer.parseInt(text));
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
