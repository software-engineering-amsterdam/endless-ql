package ql.gui.view.widgets;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Properties;

public class DatePickerWidget extends Widget {

    private JDatePickerImpl datePicker;
    private UtilDateModel model;

    private class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }

    public DatePickerWidget(QuestionModel questionModel) {
        super(questionModel);

        this.model = new UtilDateModel();

        // setup default date
        LocalDate defaultDate = (LocalDate) questionModel.getJavaTypedValue();
        this.model.setDate(defaultDate.getYear(), defaultDate.getMonthValue() - 1, defaultDate.getDayOfMonth());

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(this.model, properties);
        this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        this.model.addChangeListener(e -> {
            LocalDate newDate = LocalDate.of(model.getYear(), model.getMonth() + 1, model.getDay());
            questionModel.changeValue(newDate);
        });
    }

    @Override
    public JComponent getComponent() {
        return this.datePicker;
    }

    @Override
    public void updateValue() {
        LocalDate newDate = (LocalDate) this.getQuestionModel().getQLDataTypeValue().getValue();
        this.model.setDate(newDate.getYear(), newDate.getMonthValue() - 1, newDate.getDayOfMonth());
    }

    @Override
    public void setWidth(Integer width) {
        Dimension size = this.datePicker.getPreferredSize();
        size.width = width;
        this.datePicker.setPreferredSize(size);
    }

    @Override
    public void setFont(String font) {
        this.datePicker.setFont(new Font(font, Font.PLAIN, this.datePicker.getFont().getSize()));
    }

    @Override
    public void setFontSize(Integer fontSize) {
        this.datePicker.setFont(new Font(this.datePicker.getFont().getName(), Font.PLAIN, fontSize));
    }

    @Override
    public void setColor(String color) {
        this.datePicker.setBackground(Color.decode(color));
    }
}
