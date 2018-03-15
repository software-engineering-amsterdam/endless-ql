package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;

import java.util.List;

public class DropdownWidget extends Widget<ComboBox<String>> {

    private final List<String> options;

    public DropdownWidget(String name, ChangeListener<? super String> listener, List<String> options) {
        super(name);
        this.options = options;

        ComboBox<String> comboBox = new ComboBox<>();
        for (String option : options) {
            comboBox.getItems().add(option);
        }

        this.control = comboBox;
    }

    @Override
    public void setValue(String value) {
        this.control.setValue(value);
    }

    @Override
    public String getValue() {
        return this.control.getValue();
    }
}
