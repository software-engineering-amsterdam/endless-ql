package ql.gui.controls;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ql.gui.OnValueChange;
import ql.values.Value;

public abstract class QLControl {
    private Label label;

    public GridPane gridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(label(),0,0);
        addControl(gridPane);

        return gridPane;
    }

    public abstract Pane addControl(GridPane gridPane);

    public abstract void setValue(Value value);

    public void setLabel(String label) {
        this.label = new Label(label);
    }

    public Label label() {
        return this.label;
    }
}
