package gui.view;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.model.MixedValueHolder;
import logic.evaluators.ExpressionEvaluator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TextForm extends JPanel {

    private JComponent[] fields;

    private List<FormQuestionHolder> formQuestionHolders;
    private ExpressionEvaluator evaluator;

    // Create a form with the specified labels, tooltips, and sizes.
    public TextForm(List<FormQuestionHolder> formQuestionHolders, ExpressionEvaluator evaluator) {

        super(new BorderLayout());

        TextForm anchor = this;

        this.formQuestionHolders = formQuestionHolders;
        this.evaluator = evaluator;

        JPanel labelPanel = new JPanel(new GridLayout(formQuestionHolders.size(), 1));
        JPanel fieldPanel = new JPanel(new GridLayout(formQuestionHolders.size(), 1));

        add(labelPanel, BorderLayout.WEST);
        add(fieldPanel, BorderLayout.CENTER);

        fields = new JComponent[formQuestionHolders.size()];

        int i = 0;
        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {

            System.out.println(formQuestionHolder.getOriginalDataTypeDeclaration().toDataType().name());

            if (formQuestionHolder.getOriginalDataTypeDeclaration().toDataType() == Expression.DataType.STRING) {
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
                        formQuestionHolder.getValueHolder().setStringValue(textField.getText());
                        anchor.evaluate();
                        textField.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());
                    }
                });
                textField.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());
                fields[i] = textField;
            } else if (formQuestionHolder.getOriginalDataTypeDeclaration().toDataType() == Expression.DataType.INTEGER) {
                SpinnerModel spinnerModel = new SpinnerNumberModel(
                        0,
                        Integer.MIN_VALUE,
                        Integer.MAX_VALUE,
                        1
                );

                JSpinner spinner = new JSpinner(spinnerModel);
                spinner.addChangeListener(e -> {
                    System.out.println("Spinner value changed to: " + spinner.getValue());
                    formQuestionHolder.getValueHolder().setIntegerValue((Integer) spinner.getValue());
                    anchor.evaluate();
                    spinner.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());

                });

                spinner.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());
                fields[i] = spinner;

            } else if (formQuestionHolder.getOriginalDataTypeDeclaration().toDataType() == Expression.DataType.DECIMAL) {
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
                        formQuestionHolder.getValueHolder().setDecimalValue(new BigDecimal(safeText));
                        anchor.evaluate();
                        field.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());

                    }
                });

                field.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());
                fields[i] = field;

            } else if (formQuestionHolder.getOriginalDataTypeDeclaration().toDataType() == Expression.DataType.BOOLEAN) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.addItemListener(new ItemListener() {

                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            formQuestionHolder.getValueHolder().setBooleanValue(true);
                            System.out.println("Checkbox SELECTED");
                        } else {
                            System.out.println("Checkbox DESELECTED");
                            formQuestionHolder.getValueHolder().setBooleanValue(false);
                        }
                        anchor.evaluate();
                        checkbox.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());

                    }
                });
                checkbox.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());
                fields[i] = checkbox;

            } else {
                System.out.println("E-eeeeee");
            }

            JLabel lab = new JLabel(formQuestionHolder.getLabel(), JLabel.RIGHT);
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
        for (FormQuestionHolder formQuestionHolder : this.formQuestionHolders) {

            if (formQuestionHolder.getAssignedExpression() != null) {
                formQuestionHolder.setValueHolder(formQuestionHolder.getAssignedExpression().accept(evaluator));
            }


            if (formQuestionHolder.getVisibilityCondition() != null) {

                System.out.println("Question " +  formQuestionHolder.getVariableName() + " depends on " + formQuestionHolder.getVisibilityCondition().getMetaInformation().getText());

                MixedValueHolder xres = formQuestionHolder.getVisibilityCondition().accept(evaluator);

                System.out.println(xres.getBooleanValue());

                formQuestionHolder.setVisibilityHolder(xres);

            } else {
                formQuestionHolder.setVisibilityHolder(new MixedValueHolder(Expression.DataType.BOOLEAN, true));
            }
        }
    }

//    public String getText(int i) {
//        return (fields[i].getText());
//    }

}
