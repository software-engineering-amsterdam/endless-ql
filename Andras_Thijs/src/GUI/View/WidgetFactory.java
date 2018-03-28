package GUI.View;

import GUI.Controller.RefreshListener;
import GUI.Controller.ValueChangeListener;
import Nodes.Type;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class WidgetFactory {

    public Widget getWidget(Type type, RefreshListener listener){
        switch (type){
            case BOOL:      JCheckBox checkBox = new JCheckBox();
                            checkBox.addActionListener(new ValueChangeListener(listener));
                            return new Widget(type, checkBox);

            case STRING:    JTextField textField = new JTextField();
                            textField.addActionListener(new ValueChangeListener(listener));
                            return new Widget(type, textField);

            case INT:       JFormattedTextField intField = new JFormattedTextField(intField());
                            intField.addActionListener(new ValueChangeListener(listener));
                            return new Widget(type, intField);

            case DATE:      JFormattedTextField dateField = new JFormattedTextField(dateField());
                            dateField.addActionListener(new ValueChangeListener(listener));
                            return new Widget(type, dateField);

            case DECIMAL:   JFormattedTextField decimalField = new JFormattedTextField(decimalField());
                            decimalField.addActionListener(new ValueChangeListener(listener));
                            return new Widget(type, decimalField);

            case MONEY:     JFormattedTextField moneyField = new JFormattedTextField(decimalField());
                            moneyField.addActionListener(new ValueChangeListener(listener));
                            return new Widget(type, moneyField);

            default: return new Widget(type, new JLabel(String.valueOf(type)));
        }
    }


    private NumberFormatter intField(){
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        return formatter;
    }

    private DateFormatter dateField(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter formatter = new DateFormatter(format);
        formatter.setValueClass(Date.class);
        formatter.setAllowsInvalid(true);
        return formatter;
    }


    private NumberFormatter decimalField() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        return formatter;
    }

}
