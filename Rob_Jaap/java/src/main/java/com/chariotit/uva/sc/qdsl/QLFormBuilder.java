package com.chariotit.uva.sc.qdsl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.NumberFormatter;

import com.chariotit.uva.sc.qdsl.ast.ql.type.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;
import com.chariotit.uva.sc.qdsl.ast.ql.type.StringExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.*;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class QLFormBuilder extends JPanel {

    static DefaultFormBuilder builder;
    static JPanel panel;

    private static QLAstRoot astRoot;

    private static SymbolTable formSymbolTable;

    private static SymbolTable questionSymbolTable;

    public QLFormBuilder(QLAstRoot root) {

        super(new BorderLayout());

        this.astRoot = root;

        this.questionSymbolTable = root.getQuestionSymbolTable();

        this.formSymbolTable = root.getFormSymbolTable();

        builder = new DefaultFormBuilder(new FormLayout(""));
//        builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");

        List<Form> forms = root.getForms();

        for (Form form: forms) {
            System.out.println(form.getClass());
            renderForm(form);
        }

        panel = builder.getPanel();

        add(panel);

        QLFrame f = new QLFrame("QL Form");

        f.add(this);

        f.pack();

        f.setVisible(true);
    }

    private void renderForm(Form form){
        renderElements(form.getFormElements());
    }

    private void renderElements(List<FormElement> elements){
        for(FormElement element: elements){
            renderElement(element);
        }
    }

    private void renderElement(FormElement element){
        if (element instanceof LineElement) {
            renderLineElement((LineElement) element);
            return;
        }
        if (element instanceof IfBlock) {
            renderIfBlock((IfBlock) element);
            return;
        }
    }

    private void renderLineElement(LineElement element) {
        addQuestion(element);
    }

    private void renderIfBlock(IfBlock block) {

        if (((BooleanExpressionValue)block.getExpression().getExpressionValue()).getValue()){
            renderElements(block.getIfElements());
        } else{
            renderElements(block.getElseElements());
        }


        System.out.println("Rendering if block..");
        System.out.println(block.getExpression().getExpressionType());
        System.out.println(block.getIfElements());
    }


    // when a value is updated
    private static void updateForm() {
        ////

        panel.revalidate();
        panel.repaint();
    }

    private static JComponent componentForElement(LineElement element){

        // TODO refactor this!!
        ExpressionType type = element.getTypeExpression().getTypeNode().getType();

        switch (type){
            case BOOLEAN:   return checkBoxComponent(element);
            case STRING:    return textComponent(element);
            case MONEY:     return currencyComponent(element);
            case INTEGER:   return numericComponent(element);
            default:        return textComponent(element);
        }
    }

    private static JTextField textComponent(LineElement element) {

        JTextField textField = new JTextField();

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

                SymbolTableEntry symbol = questionSymbolTable.getEntry(element.getLabel().getLabel());

                ((StringExpressionValue)symbol.getExpressionValue()).setValue(textField.getText());

                System.out.println("Generic text field updated");
                updateForm();
            }
        });

        // return the text field that is configured by the formatter
        return textField;
    }


    private static JCheckBox checkBoxComponent(LineElement element) {

        // get the symbol table entry
        SymbolTableEntry symbol = questionSymbolTable.getEntry(element.getLabel().getLabel());

        JCheckBox checkbox = new JCheckBox();

        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                System.out.println(checkbox.isSelected());

                SymbolTableEntry symbol = questionSymbolTable.getEntry(element.getLabel().getLabel());

//                ((BooleanExpressionValue)symbol.getExpressionValue()).setValue(checkbox.isSelected());

                System.out.println(symbol.getExpressionValue());
                updateForm();
            }
        });

        // return the text field that is configured by the formatter
        return checkbox;
    }

    private static JFormattedTextField currencyComponent(LineElement element) {

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

                SymbolTableEntry symbol = questionSymbolTable.getEntry(element.getLabel().getLabel());

                ((StringExpressionValue)symbol.getExpressionValue()).setValue(textField.getText());

                System.out.println("Currency text field updated");
                updateForm();
            }
        });


        // return the text field that is configured by the formatter
        return textField;
    }

    private static JFormattedTextField numericComponent(LineElement element) {

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

                SymbolTableEntry symbol = questionSymbolTable.getEntry(element.getLabel().getLabel());

                ((StringExpressionValue)symbol.getExpressionValue()).setValue(textField.getText());

                updateForm();
            }
        });

        // return the text field that is configured by the formatter
        return textField;
    }

    public static void addQuestion(LineElement element){

        JComponent questionComponent = componentForElement(element);

        builder.append(element.getQuestion().getQuestion(), questionComponent);
        builder.nextLine();
    }

}
