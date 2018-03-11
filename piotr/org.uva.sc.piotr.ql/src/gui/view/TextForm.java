package gui.view;

import gui.model.FormBlock;
import types.DataType;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import static types.DataType.*;

public class TextForm extends JPanel {

    private JComponent[] fields;

    // Create a form with the specified labels, tooltips, and sizes.
    public TextForm(List<FormBlock> formBlocks) {
        super(new BorderLayout());
        JPanel labelPanel = new JPanel(new GridLayout(formBlocks.size(), 1));
        JPanel fieldPanel = new JPanel(new GridLayout(formBlocks.size(), 1));
        add(labelPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);
        fields = new JComponent[formBlocks.size()];

        int i = 0;
        for (FormBlock block : formBlocks) {

            System.out.println(block.getVariableType().name());

            if (block.getVariableType() == Type.STRING) {
                JTextField textField = new JTextField();
                textField.setColumns(20);
                textField.getDocument().addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        warn();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        warn();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        warn();
                    }

                    private void warn() {
                        System.out.println("Text changed to: " + textField.getText());
                    }
                });

                fields[i] = textField;
            } else if (block.getVariableType() == Type.INTEGER) {
                SpinnerModel spinnerModel = new SpinnerNumberModel(
                        0,
                        Integer.MIN_VALUE,
                        Integer.MAX_VALUE,
                        1
                );

                JSpinner spinner = new JSpinner(spinnerModel);
                spinner.addChangeListener(new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                        System.out.println("Spinner value changed to: " + spinner.getValue());

                    }
                });

                fields[i] = spinner;

            } else if (block.getVariableType() == Type.DECIMAL) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setGroupingUsed(false);
                NumberFormatter formatter = new NumberFormatter(format);
//                // If you want the value to be committed on each keystroke instead of focus lost
                formatter.setCommitsOnValidEdit(true);
                JFormattedTextField field = new JFormattedTextField(formatter);
                field.setColumns(15);
                field.getDocument().addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        warn();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        warn();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        warn();
                    }

                    private void warn() {
                        System.out.println("Decimal changed to: " + field.getText());
                    }
                });
                fields[i] = field;

            } else if (block.getVariableType() == Type.BOOLEAN) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.addItemListener(new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        System.out.print("Checkbox ");
                        System.out.println(e.getStateChange() == ItemEvent.SELECTED
                                ? "SELECTED" : "DESELECTED");
                    }
                });
                fields[i] = checkbox;

            } else {
                System.out.println("E-eeeeee");
            }

            JLabel lab = new JLabel(block.getLabel(), JLabel.RIGHT);
            lab.setLabelFor(fields[i]);
            labelPanel.add(lab);

            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(fields[i]);
            fieldPanel.add(p);

            i++;
        }
    }

//    public String getText(int i) {
//        return (fields[i].getText());
//    }

}
