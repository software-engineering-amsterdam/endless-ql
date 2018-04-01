package gui.listeners;

import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BoolActionListener
 * Called when a jCheckbox control is changed
 */
public class DateActionListener implements ActionListener {

    private JDatePicker datePicker;
    private DateValue value;

    public DateActionListener(DateValue value, JDatePicker datePicker) {
        this.value = value;
        this.datePicker = datePicker;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date selectedDate = (Date) datePicker.getModel().getValue();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String reportDate = df.format(selectedDate);
        JOptionPane.showMessageDialog(null, reportDate);

        value.setValue(selectedDate);
    }
}