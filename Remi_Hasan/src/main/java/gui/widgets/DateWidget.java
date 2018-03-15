package gui.widgets;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;

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
        // TODO: date format
        return this.control.getValue().toString();
    }

    public void setListener(ChangeListener<LocalDate> changeListener) {
        this.control.valueProperty().addListener(changeListener);
    }
}