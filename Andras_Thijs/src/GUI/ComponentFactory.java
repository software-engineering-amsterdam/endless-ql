package GUI;

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

    public Component getComponent(Type type){
        switch (type){
            case BOOL: return new JCheckBox();

            case STRING: return new JTextField();

            case INT: return new JFormattedTextField(intField());

            case DATE: return new JFormattedTextField(dateField());

            case DECIMAL: return new JFormattedTextField(decimalField());

            case MONEY: return new JFormattedTextField(moneyField());

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

    // TODO define currency field
    private NumberFormatter moneyField() {
        return decimalField();
    }
}
