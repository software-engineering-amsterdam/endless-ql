package gui.questions;

import QL.classes.Question;
import QL.classes.values.Value;
import gui.GUIBuilder;
import gui.listeners.QuestionListener;
import org.jdatepicker.*;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.Properties;

public class QuestionWidgetDate extends QuestionPanel {

    private JDatePicker picker;
    private UtilDateModel model;
    private Calendar cal;

    public QuestionWidgetDate(String key, Question question) {
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
    public void setQuestionChangeListener(QuestionListener questionListener) {
        picker.addActionListener(questionListener.new DateActionListener(super.getQuestion().getId(), picker));
    }

    @Override
    public void setWidgetFixed() {
        this.picker.setEnabled(false);
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
