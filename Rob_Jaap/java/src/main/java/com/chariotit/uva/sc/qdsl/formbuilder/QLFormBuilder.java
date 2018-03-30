package com.chariotit.uva.sc.qdsl.formbuilder;

import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;
import com.chariotit.uva.sc.qdsl.ast.ql.type.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ql.type.MoneyExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.type.StringExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.EvaluateVisitor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class QLFormBuilder {

    private static Integer lineHeight = 40;
    private static Integer labelWidth = 200;
    private static Integer inputWidth = 200;

    private QLAstRoot astRoot;
    private JFrame jFrame;
    private SymbolTable symbolTable;
    private HashMap<LineElement, FormQuestion> questions;
    private VisibilityChecker visibilityChecker;

    public QLFormBuilder(QLAstRoot astRoot) {
        this.astRoot = astRoot;
        this.symbolTable = astRoot.getQuestionSymbolTable();

        this.jFrame = new JFrame();
        this.jFrame.setVisible(true);


        this.questions = FormQuestionMapBuilder.buildMap(astRoot, this);
        this.visibilityChecker = new VisibilityChecker(questions, astRoot);
        this.render();
    }

    private void render() {
        updateForm();
    }


    private void updateForm() {
        this.evaluateAst();
        this.visibilityChecker.checkVisibility();

        renderQuestions();

    }

    private void renderQuestions() {
        Integer currentLine = 0;

        for (Map.Entry<LineElement, FormQuestion> entry : questions.entrySet()) {
            System.out.println(entry.getValue().getVisible());
            FormQuestion question = entry.getValue();

            if (question.getVisible()) {

                // x y width height
                question.getLabel().setBounds(0, currentLine * lineHeight,
                        labelWidth, lineHeight);
                question.getComponent().setBounds(labelWidth, lineHeight * currentLine,
                        inputWidth, lineHeight);

                jFrame.add(question.getLabel());
                jFrame.add(question.getComponent());

                currentLine += 1;
            }
        }

        jFrame.revalidate();
        jFrame.repaint();
    }

    private void evaluateAst() {
        EvaluateVisitor evaluateVisitor = new EvaluateVisitor(symbolTable);
        astRoot.acceptVisitor(evaluateVisitor);
    }

    protected JComponent componentForElement(LineElement element) {

        // TODO refactor this!!
        ExpressionType type = element.getTypeExpression().getTypeNode().getType();

        switch (type) {
            case BOOLEAN:
                return checkBoxComponent(element);
            case STRING:
                return textComponent(element);
            case MONEY:
                return currencyComponent(element);
            case INTEGER:
                return numericComponent(element);
            default:
                return textComponent(element);
        }
    }

    private JTextField textComponent(LineElement element) {

        JTextField textField = new JTextField();

        SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

        textField.setText(((StringExpressionValue)symbol.getExpressionValue()).getValue());

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void removeUpdate(DocumentEvent e) {
                update();
            }

            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void update() {

                SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                ((StringExpressionValue) symbol.getExpressionValue()).setValue(textField.getText());

                System.out.println("Generic text field updated");
                updateForm();
            }
        });

        // return the text field that is configured by the formatter
        return textField;
    }


    private JCheckBox checkBoxComponent(LineElement element) {

        // get the symbol table entry
        SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

        JCheckBox checkbox = new JCheckBox();

        checkbox.setSelected(((BooleanExpressionValue) symbol.getExpressionValue()).getValue());

        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(checkbox.isSelected());

                SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                System.out.println(((BooleanExpressionValue) symbol.getExpressionValue()).getValue());


                ((BooleanExpressionValue) symbol.getExpressionValue()).setValue(checkbox.isSelected());

                updateForm();
            }


        });

        // return the text field that is configured by the formatter
        return checkbox;
    }

    private JFormattedTextField currencyComponent(LineElement element) {

        //initialize the settings
        NumberFormat options = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        options.setMaximumFractionDigits(0);

        // create a new formatter
        NumberFormatter formatter = new NumberFormatter(options);

        // ensure that overflow is not possible
        formatter.setMinimum(Double.MIN_VALUE);
        formatter.setMaximum(Double.MAX_VALUE);

        // no values other than the format are allowed
        formatter.setAllowsInvalid(true);

        JFormattedTextField textField = new JFormattedTextField(formatter);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void removeUpdate(DocumentEvent e) {
                update();
            }

            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void update() {

                SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                ((MoneyExpressionValue) symbol.getExpressionValue()).setValue(Float
                        .parseFloat(textField.getText()));

                System.out.println("Currency text field updated");
                updateForm();
            }
        });


        // return the text field that is configured by the formatter
        return textField;
    }

    private JFormattedTextField numericComponent(LineElement element) {

        // create a new formatter
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());

        // setup the formatter
        formatter.setValueClass(Integer.class);

        // ensure that overflow is not possible
        formatter.setMinimum(Integer.MIN_VALUE);
        formatter.setMaximum(Integer.MAX_VALUE);

        // no values other than the format are allowed
        formatter.setAllowsInvalid(false);

        JFormattedTextField textField = new JFormattedTextField(formatter);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void removeUpdate(DocumentEvent e) {
                update();
            }

            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void update() {

                SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                ((StringExpressionValue) symbol.getExpressionValue()).setValue(textField.getText());

                updateForm();
            }
        });

        // return the text field that is configured by the formatter
        return textField;
    }

}
