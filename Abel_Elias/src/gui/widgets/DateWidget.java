package gui.widgets;

import QL.classes.values.DateValue;
import org.jdatepicker.JDatePicker;

import java.util.Calendar;

public class DateWidget implements Widget {
    private JDatePicker picker;
    private DateValue value;
    private Calendar cal;

    public DateWidget(DateValue value) {
        this.value = value;
        this.cal = Calendar.getInstance();
        picker = new JDatePicker();
        refresh();
    }

    @Override
    public JDatePicker getJComponent() {
        return picker;
    }

    @Override
    public void refresh() {
        cal.setTime(value.getValue());

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        this.picker.getModel().setDate(year, month, day);
    }
}
