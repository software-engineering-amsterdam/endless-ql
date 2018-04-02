package com.chariotit.uva.sc.qdsl.formbuilder;

import com.chariotit.uva.sc.qdsl.ast.ql.node.*;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTable;
import com.chariotit.uva.sc.qdsl.ast.ql.symboltable.SymbolTableEntry;
import com.chariotit.uva.sc.qdsl.ast.ql.type.*;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.EvaluateVisitor;

import com.chariotit.uva.sc.qdsl.ast.qls.node.Page;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Question;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Section;
import com.chariotit.uva.sc.qdsl.ast.qls.node.SectionElement;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Stylesheet;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class QLFormBuilder {

    private static Integer lineHeight       = 40;
    private static Integer lineMargin       = 20;
    private static Integer labelWidth       = 400;
    private static Integer inputWidth       = 300;
    private static Integer contentMargin    = 14;

    private static Integer frameWidth = 600;
    private static Integer frameHeight = 600;

    private QLAstRoot astRoot;
    private JFrame jFrame;
    private Stylesheet stylesheet;
    private SymbolTable symbolTable;
    private LinkedHashMap<LineElement, FormQuestion> questions;
    private VisibilityChecker visibilityChecker;

    public QLFormBuilder(QLAstRoot astRoot) {
        this.astRoot = astRoot;
        this.render();
    }

    public QLFormBuilder(QLAstRoot astRoot, Stylesheet stylesheet) {
        this.astRoot = astRoot;
        this.stylesheet = stylesheet;
        this.render();
    }

    private void render() {

        this.symbolTable = this.astRoot.getQuestionSymbolTable();

        this.jFrame = new JFrame();
        this.jFrame.setVisible(true);
        this.jFrame.setSize(frameWidth, frameHeight);


        this.questions = FormQuestionMapBuilder.buildMap(this.astRoot, this);
        this.visibilityChecker = new VisibilityChecker(questions, astRoot, this);

        updateForm();
    }

    private void updateForm() {

        this.evaluateAst();

        this.visibilityChecker.checkVisibility();

        if (this.stylesheet != null) {
            renderQuestions(this.stylesheet);
        } else {
            renderQuestions();
        }

    }

    private void renderQuestions() {
        int currentLine = 0;

        for (Map.Entry<LineElement, FormQuestion> entry : questions.entrySet()) {
            FormQuestion question = entry.getValue();

            if (question.getVisible()) {

                jFrame.add(question.getLabel());
                jFrame.add(question.getComponent());


                question.getLabel().setBounds(contentMargin, currentLine * lineHeight + lineMargin,
                        labelWidth, lineHeight);
                question.getComponent().setBounds(labelWidth, currentLine * lineHeight + lineMargin,
                        inputWidth, lineHeight);

                currentLine += 1;
            }
        }


        // This is a workaround. Somehow the x component of the last element is reset to 0 after
        // revalidate. Insert new boguselement as a workaround
        JLabel bugFix = new JLabel("");
        jFrame.add(bugFix);
        bugFix.setBounds(contentMargin, currentLine * lineHeight + lineMargin, 0, 0);

        jFrame.revalidate();
        jFrame.repaint();
   }

    private void renderQuestions(Stylesheet stylesheet) {
        for (Page page : stylesheet.getPages()) {
            System.out.println("We have a page here " + page.getLabel());
            renderPage(page);
        }
    }

    private void renderPage(Page page){
        for (Section section: page.getSections()) {
            renderSection(section);
        }
    }

    private void renderSection(Section section){

        System.out.println(questions);

        for (SectionElement element: section.getElements()) {
            if(element instanceof Question){
                FormQuestion formQuestion = questions.get(((Question) element).getLabel());
                System.out.println("rendering element " + ((Question) element).getLabel());

            }
        }
    }

    private void evaluateAst() {
        EvaluateVisitor evaluateVisitor = new EvaluateVisitor(symbolTable);
        astRoot.acceptVisitor(evaluateVisitor);
    }

    protected JComponent componentForElement(LineElement element) {

        Expression expression = element.getTypeExpression().getExpression();

        // check if this element is a computed element
        if(expression != null){
            switch (expression.getExpressionType()) {
                case MONEY:
                    JLabel moneyLabel = new JLabel();
                    MoneyExpressionValue moneyExpressionValue = (MoneyExpressionValue)expression.getExpressionValue();
                    if(moneyExpressionValue != null) { moneyLabel.setText(moneyExpressionValue.getValue().toString()); }
                    return moneyLabel;
                case INTEGER:
                    JLabel integerLabel = new JLabel();
                    IntegerExpressionValue integerExpressionValue = (IntegerExpressionValue)expression.getExpressionValue();
                    if(integerExpressionValue != null) { integerLabel.setText(integerExpressionValue.getValue().toString()); }
                    return integerLabel;
                case BOOLEAN:
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setEnabled(false);
                    BooleanExpressionValue booleanExpressionValue = (BooleanExpressionValue) expression.getExpressionValue();
                    if(booleanExpressionValue != null) { checkBox.setSelected(booleanExpressionValue.getValue()); }
                    return checkBox;
            }
        }

        // otherwise we get the type of the element to render an input
        ExpressionType type = element.getTypeExpression().getTypeNode().getType();

        switch (type) {
            case BOOLEAN:
                return checkBoxComponent(element, true);
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

                // only perform this operation if the textfield is not empty
                if (!textField.getText().isEmpty()) {

                    SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                    ((StringExpressionValue) symbol.getExpressionValue()).setValue(textField.getText());

                    updateForm();
                }
            }
        });

        // return the text field that is configured by the formatter
        return textField;
    }

    private JCheckBox checkBoxComponent(LineElement element, Boolean editable) {

        // get the symbol table entry
        SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

        JCheckBox checkbox = new JCheckBox();

        checkbox.setSelected(((BooleanExpressionValue) symbol.getExpressionValue()).getValue());

        if (!editable) {
            checkbox.setSelected(true);
            checkbox.setEnabled(false);
            return checkbox;
        }

        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

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

        JFormattedTextField textField = new JFormattedTextField();

        SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

        textField.setText(((MoneyExpressionValue) symbol.getExpressionValue()).getValue().toString());

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

                // only perform this operation if the textfield is not empty
                if (!textField.getText().isEmpty()) {

                    SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                    ((MoneyExpressionValue) symbol.getExpressionValue()).setValue(Float
                            .parseFloat(textField.getText()));

                    updateForm();

                    textField.requestFocus();

                }
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

                // only perform this operation if the textfield is not empty
                if (!textField.getText().isEmpty()) {

                    SymbolTableEntry symbol = symbolTable.getEntry(element.getLabel().getLabel());

                    ((StringExpressionValue) symbol.getExpressionValue()).setValue(textField.getText());

                    updateForm();
                }
            }
        });

        // return the text field that is configured by the formatter
        return textField;
    }

}
