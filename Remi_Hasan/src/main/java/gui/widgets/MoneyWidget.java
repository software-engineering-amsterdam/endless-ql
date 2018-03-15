package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MoneyWidget extends Widget<TextField> {

    public MoneyWidget(ChangeListener listener, String name) {
        super(name);

        TextField textField = new TextField();
        TextFormatter formatter = createTextFormatter("-?\\d*(\\.\\d{0,2})?");
        textField.setTextFormatter(formatter);

        textField.textProperty().addListener(listener);

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
