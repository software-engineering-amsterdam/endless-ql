package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.time.LocalDate;

public class DateWidget extends Widget<DatePicker> {

    public DateWidget(String name) {
        super(name);

        this.control = new DatePicker();
    }

    @Override
    public void setValue(String value) {
        this.control.setValue(LocalDate.parse(value));
    }

    @Override
    public String getValue() {
        // TODO: format
        return this.control.getValue().toString();
    }
}
