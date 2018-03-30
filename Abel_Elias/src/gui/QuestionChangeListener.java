package gui;

import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.StringValue;
import QL.classes.values.UndefinedValue;
import QL.classes.values.Value;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuestionChangeListener {

    private final GUIBuilder GUIBuilder;

    public QuestionChangeListener(GUIBuilder GUIBuilder) {
        this.GUIBuilder = GUIBuilder;
    }

    /**
     ******************************************************************************************
     *ActionListener methods
     ******************************************************************************************
     */

    /**
     * DateActionListener
     * Called when a jDatePicker control is changed
     */
    public class DateActionListener implements ActionListener {

        private JDatePicker picker;
        private String key;

        public DateActionListener(String key, JDatePicker picker) {
            this.key = key;
            this.picker = picker;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Date selectedDate = (Date) picker.getModel().getValue();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            String reportDate = df.format(selectedDate);
            JOptionPane.showMessageDialog(null, reportDate);
        }
    }

    /**
     * BoolActionListener
     * Called when a jCheckbox control is changed
     */
    public class BoolActionListener implements ActionListener {

        private JCheckBox checkBox;
        private String key;

        public BoolActionListener(String key, JCheckBox checkBox) {
            this.key = key;
            this.checkBox = checkBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkBox.isSelected()) {
                GUIBuilder.onQuestionChange(key, new BooleanValue(true));
            } else {
                GUIBuilder.onQuestionChange(key, new BooleanValue(false));

            }
            checkBox.requestFocus();
        }
    }

    /**
     * StringActionListener
     * Called when a jTextField control is changed
     * in the case of a String questionPanel
     */
    public class StringDocumentListener implements DocumentListener {
        private boolean modified = false;
        private JTextField textField;
        private String key;

        public StringDocumentListener(String key, JTextField textField) {
            this.key = key;
            this.textField = textField;
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            actionCalled();
        }

        private void actionCalled() {
            textField.requestFocus();
            if (!modified) {
                modified = true;
                SwingUtilities.invokeLater(new Runnable() {
                                               @Override
                                               public void run() {
                                                   String textString = textField.getText();
                                                   GUIBuilder.onQuestionChange(key, new StringValue(textString));
                                                   modified = false;
                                                   textField.requestFocus();
                                               }
                                           }
                );
            }
        }
    }

    /**
     * StringActionListener
     * Called when a jTextField control is changed
     * in the case of a Integer questionPanel
     */
    public class IntegerDocumentListener implements DocumentListener {
        private boolean modified = false;
        private JTextField textField;
        private String key;

        public IntegerDocumentListener(String key, JTextField textField2) {
            this.key = key;
            this.textField = textField2;
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            actionCalled();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            actionCalled();
        }

        private void actionCalled() {
            textField.requestFocus();
            if (!modified) {
                modified = true;
                SwingUtilities.invokeLater(
                        new Runnable() {
                            @Override
                            public void run() {
                                boolean correctInput = true;
                                int input = 0;
                                try {
                                    String textString = textField.getText();
                                    input = Integer.parseInt(textString);
                                } catch (NumberFormatException exception) {
                                    correctInput = false;
                                }
                                if (correctInput) {
                                    GUIBuilder.onQuestionChange(key, new IntegerValue(input));
                                } else {
                                    GUIBuilder.onQuestionChange(key, new UndefinedValue());
                                }
                                modified = false;
                                textField.requestFocus();
                            }
                        }
                );
            }
        }

    }
}

