package ql.gui.controls;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ql.ast.expressions.Identifier;
import ql.gui.OnValueChange;
import ql.values.BooleanValue;
import ql.values.Value;

public class QLCheckBox extends QLControl {
    private final CheckBox checkBox;

    public QLCheckBox(Identifier identifier, OnValueChange onValueChange) {
        this.checkBox = new CheckBox();
        this.checkBox.setOnAction(event -> onValueChange.changed(identifier, value()));
    }

    private BooleanValue value() {
        return new BooleanValue(this.checkBox.isSelected());
    }

    @Override
    public void setValue(Value value) {
        if (value.getValue() != null) {
            this.checkBox.setSelected((Boolean) value.getValue());
        }
    }

    @Override
    public Pane addControl(GridPane gridPane) {
        gridPane.getChildren().add(this.checkBox);
        return gridPane;
    }
}
