package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class IntegerWidget extends Widget<TextField> {

    public IntegerWidget(String name) {
        super(name);

        TextField textField = new TextField();
        TextFormatter formatter = createTextFormatter("-?\\d*");
        textField.setTextFormatter(formatter);

        this.control = textField;
    }

    @Override
    public void setValue(String value) {
        this.control.setText(value);
    }

    @Override
    public String getValue() {
        return this.control.getText();
    }
}
