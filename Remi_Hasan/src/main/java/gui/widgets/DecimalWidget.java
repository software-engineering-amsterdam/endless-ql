package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class DecimalWidget extends Widget<TextField> {

    public DecimalWidget(String name) {
        super(name);

        TextField textField = new TextField();
        TextFormatter formatter = createTextFormatter("-?\\d*(\\.\\d*)?");
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

    public void setListener(ChangeListener<String> changeListener) {
        this.control.textProperty().addListener(changeListener);
    }
}