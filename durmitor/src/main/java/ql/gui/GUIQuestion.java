package ql.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Int;
import ql.ast.type.Money;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.gui.fields.DateSpinnerField;
import ql.gui.fields.IntTextField;
import ql.gui.fields.MoneyTextField;
import ql.gui.fields.StrTextField;

public class GUIQuestion extends JPanel {

    private static final long serialVersionUID = 2816798767896918152L;
    ResourceBundle translations = ResourceBundle.getBundle("ql.i18n.gui");
    private JCheckBox checkBox;
    private JTextField textField;
    private DateSpinnerField dateSpinner;
    
    public GUIQuestion(String label, final Type variableType) {
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setLayout(new GridLayout(1,2));
        add(new JLabel(label));
        
        if(variableType.isBoolean()) {
            Bool variable = (Bool) variableType;
            checkBox = new JCheckBox(translations.getString("jcheckbox.label"));
            checkBox.setName(variable.getName());
            checkBox.setSelected(false);
            checkBox.setEnabled(true);
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    variable.setValue(checkBox.isSelected());
                }
            });
            add(this.checkBox);
        }
        else if(variableType.isDate()) {
            Date variable = (Date) variableType;
            dateSpinner = new DateSpinnerField(variable);
            add(this.dateSpinner);
        }
        else if(variableType.isDecimal()) {
                    
        }
        else if(variableType.isInteger()) {
            Int variable = (Int) variableType;
            textField = new IntTextField(variable);
            add(this.textField);
        }
        else if(variableType.isMoney()) {
            Money variable = (Money) variableType;
            textField = new MoneyTextField(variable);
            add(this.textField);
        }
        else if(variableType.isNumeric()) {
            
        }
        else if(variableType.isString()) {
            Str variable = (Str) variableType;
            textField = new StrTextField(variable);
            add(this.textField);
        }
    }
}
