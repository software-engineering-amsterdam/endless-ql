package GUI;

import GUI.Listeners.RefreshListener;
import GUI.Listeners.ValueChangeListener;
import Nodes.Type;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComponentFactory {

    public Component getComponent(Type type, RefreshListener listener){
        switch (type){
            case BOOL:      JCheckBox checkBox = new JCheckBox();
                            checkBox.addActionListener(new ValueChangeListener(listener));
                            return checkBox;

            case STRING:    JTextField textField = new JTextField();
                            textField.addActionListener(new ValueChangeListener(listener));
                            return textField;

            case INT:       JFormattedTextField intField = new JFormattedTextField(intField());
                            intField.addActionListener(new ValueChangeListener(listener));
                            return intField;

            case DATE:      JFormattedTextField dateField = new JFormattedTextField(dateField());
                            dateField.addActionListener(new ValueChangeListener(listener));
                            return dateField;

            case DECIMAL:   JFormattedTextField decimalField = new JFormattedTextField(decimalField());
                            decimalField.addActionListener(new ValueChangeListener(listener));
                            return decimalField;

            case MONEY:     JFormattedTextField moneyField = new JFormattedTextField(decimalField());
                            moneyField.addActionListener(new ValueChangeListener(listener));
                            return moneyField;

            default: return new JLabel(String.valueOf(type));
        }
    }


    private NumberFormatter intField(){
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        return formatter;
    }

    private DateFormatter dateField(){
        DateFormat format = new SimpleDateFormat("yyyy--MMMM--dd");
        DateFormatter formatter = new DateFormatter(format);
        formatter.setValueClass(Date.class);
        formatter.setAllowsInvalid(false);
        return formatter;
    }


    private NumberFormatter decimalField() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        return formatter;
    }

}
