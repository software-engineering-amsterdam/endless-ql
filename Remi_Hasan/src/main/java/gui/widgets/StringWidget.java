package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class StringWidget extends Widget<TextField> {

    public StringWidget(ChangeListener listener, String name) {
        super(name);

        this.control = new TextField();
        this.control.textProperty().addListener(listener);
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
