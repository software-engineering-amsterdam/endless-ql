package gui.questions;

import classes.Question;
import classes.values.Value;
import gui.FormBuilder;
import org.jdatepicker.*;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.Properties;

public class QuestionPanelDate extends QuestionPanel {

    private JDatePicker picker;
    private UtilDateModel model;
    private Calendar cal;

    public QuestionPanelDate(String key, Question question) {
        super(key, question);
        createControlWidget(key);
    }

    @Override
    public void createControlWidget(String key) {
        cal = Calendar.getInstance();
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        picker = new JDatePicker(model);
        this.add(picker);
    }

    @Override
    public JComponent getComponent() {
        return this.picker;
    }

    @Override
    public void setListener(EventListener listener) {
        FormBuilder.DateActionListener dateActionListener = (FormBuilder.DateActionListener) listener;
        picker.addActionListener(dateActionListener);
    }

    @Override
    public void setValue(Value value) {
        Date date = (Date) value.getValue();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        this.picker.getModel().setDate(year, month, day);
    }
}
