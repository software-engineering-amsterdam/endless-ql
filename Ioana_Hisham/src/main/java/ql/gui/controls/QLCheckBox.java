package ql.gui.controls;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ql.values.BooleanValue;
import ql.values.Value;

public class QLCheckBox extends QLControl {
    private final CheckBox checkBox;

    public QLCheckBox() {
        this.checkBox = new CheckBox();
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
