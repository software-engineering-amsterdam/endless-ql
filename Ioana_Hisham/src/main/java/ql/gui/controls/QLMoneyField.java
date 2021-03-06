package ql.gui.controls;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ql.ast.expressions.Identifier;
import ql.gui.OnValueChange;
import ql.values.IntegerValue;
import ql.values.Value;

public class QLMoneyField extends QLControl {
    private final TextField textField;
    private final Spinner spinner;

    public QLMoneyField(Identifier identifier, OnValueChange onValueChange) {
        this.textField = new TextField();
        spinner = new Spinner();
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1
        ));
        this.spinner.setOnMouseClicked(event -> changeValue(identifier, onValueChange));
    }

    private void changeValue(Identifier identifier, OnValueChange onValueChange) {
        IntegerValue value;
        if (textField.getText().isEmpty()) {
            value = new IntegerValue(0);
        } else {
            value = new IntegerValue(Integer.parseInt(textField.getText()));
        }
        onValueChange.changed(identifier, value(spinner));
    }

    private Value value(Spinner<Integer> spinner) {
        return new IntegerValue(spinner.getValue());
    }

    @Override
    public void setValue(Value value) {
        if (value.getValue() != null) {
            spinner.getValueFactory().setValue(value.getValue());
        }
    }

    @Override
    public Pane addControl(GridPane gridPane) {
        gridPane.add(this.spinner,1,0);
        return gridPane;
    }
}
