package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;

public class CheckboxWidget extends Widget<CheckBox> {

    public CheckboxWidget(ChangeListener listener, String name) {
        super(name);

        this.control = new CheckBox();
        this.control.selectedProperty().addListener(listener);
    }

    @Override
    public void setValue(String value) {
        this.control.setSelected(Boolean.valueOf(value));
    }

    @Override
    public String getValue() {
        return String.valueOf(this.control.isSelected());
    }
}
