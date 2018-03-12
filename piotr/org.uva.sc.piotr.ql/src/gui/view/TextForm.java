package gui.view;

import ast.model.expressions.Expression;
import ast.visitors.evaluators.ExpressionEvaluator;
import ast.visitors.evaluators.ExpressionResult;
import gui.model.FormQuestion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

public class TextForm extends JPanel {

    private JComponent[] fields;

    private List<FormQuestion> formQuestions;
    private ExpressionEvaluator evaluator;

    // Create a form with the specified labels, tooltips, and sizes.
    public TextForm(List<FormQuestion> formQuestions, ExpressionEvaluator evaluator) {

        super(new BorderLayout());

        TextForm anchor = this;

        this.formQuestions = formQuestions;
        this.evaluator = evaluator;

        JPanel labelPanel = new JPanel(new GridLayout(formQuestions.size(), 1));
        JPanel fieldPanel = new JPanel(new GridLayout(formQuestions.size(), 1));

        add(labelPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);

        fields = new JComponent[formQuestions.size()];

        int i = 0;
        for (FormQuestion formQuestion : formQuestions) {

            System.out.println(formQuestion.getVariableDataType().name());

            if (formQuestion.getVariableDataType() == Expression.DataType.STRING) {
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
                        formQuestion.getValue().setStringValue(textField.getText());
                        anchor.evaluate();
                        textField.setVisible(formQuestion.getVisibility().getBooleanValue());
                    }
                });
                textField.setVisible(formQuestion.getVisibility().getBooleanValue());
                fields[i] = textField;
            } else if (formQuestion.getVariableDataType() == Expression.DataType.INTEGER) {
                SpinnerModel spinnerModel = new SpinnerNumberModel(
                        0,
                        Integer.MIN_VALUE,
                        Integer.MAX_VALUE,
                        1
                );

                JSpinner spinner = new JSpinner(spinnerModel);
                spinner.addChangeListener(e -> {
                    System.out.println("Spinner value changed to: " + spinner.getValue());
                    formQuestion.getValue().setIntegerValue((Integer) spinner.getValue());
                    anchor.evaluate();
                    spinner.setVisible(formQuestion.getVisibility().getBooleanValue());

                });

                spinner.setVisible(formQuestion.getVisibility().getBooleanValue());
                fields[i] = spinner;

            } else if (formQuestion.getVariableDataType() == Expression.DataType.DECIMAL) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setGroupingUsed(false);
                NumberFormatter formatter = new NumberFormatter(format);
//                // If you want the value to be committed on each keystroke instead of focus lost
                formatter.setCommitsOnValidEdit(true);
                JFormattedTextField field = new JFormattedTextField(formatter);
                field.setColumns(15);
                field.getDocument().addDocumentListener(new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("changed update");
                        warn();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("remove update");
                        warn();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("insert update");
                        warn();
                    }

                    private void warn() {
                        String clearText = field.getText().replaceAll("[^0-9.]", "");
                        String safeText = clearText.equals("") ? "0" : clearText;
                        System.out.println("Decimal changed to: " + safeText);
                        formQuestion.getValue().setDecimalValue(new BigDecimal(safeText));
                        anchor.evaluate();
                        field.setVisible(formQuestion.getVisibility().getBooleanValue());

                    }
                });

                field.setVisible(formQuestion.getVisibility().getBooleanValue());
                fields[i] = field;

            } else if (formQuestion.getVariableDataType() == Expression.DataType.BOOLEAN) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.addItemListener(new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            formQuestion.getValue().setBooleanValue(true);
                            System.out.println("Checkbox SELECTED");
                        } else {
                            System.out.println("Checkbox DESELECTED");
                            formQuestion.getValue().setBooleanValue(false);
                        }
                        anchor.evaluate();
                        checkbox.setVisible(formQuestion.getVisibility().getBooleanValue());

                    }
                });
                checkbox.setVisible(formQuestion.getVisibility().getBooleanValue());
                fields[i] = checkbox;

            } else {
                System.out.println("E-eeeeee");
            }

            JLabel lab = new JLabel(formQuestion.getLabel(), JLabel.RIGHT);
            lab.setLabelFor(fields[i]);
            labelPanel.add(lab);


            JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            p.add(fields[i]);
            fieldPanel.add(p);

            i++;
        }
    }

    private void evaluate() {
        // initial evaluation
        for (FormQuestion formQuestion : this.formQuestions) {
            if (formQuestion.getAssignedExpression() != null) {
                formQuestion.setValue(formQuestion.getAssignedExpression().accept(evaluator));
            }
            if (formQuestion.getVisibilityCondition() != null) {

                System.out.println(formQuestion.getVisibilityCondition());

                ExpressionResult xres = formQuestion.getVisibilityCondition().accept(evaluator);

                System.out.println(xres);

                formQuestion.setVisibility(xres);

            } else {
                formQuestion.setVisibility(new ExpressionResult(Expression.DataType.BOOLEAN, true));
            }
        }
    }

//    public String getText(int i) {
//        return (fields[i].getText());
//    }

}
