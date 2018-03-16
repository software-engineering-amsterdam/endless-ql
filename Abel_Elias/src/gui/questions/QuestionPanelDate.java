package gui.questions;

import classes.Question;
import gui.FormBuilder;
import org.jdatepicker.*;

import javax.swing.*;
import java.util.EventListener;
import java.util.Properties;

public class QuestionPanelDate extends QuestionPanel {

    private JDatePicker picker;

    public QuestionPanelDate(String key, Question question) {
        super(key, question);
        createControlWidget(key);
    }

    @Override
    public void createControlWidget(String key) {
        UtilDateModel model = new UtilDateModel();
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

}
