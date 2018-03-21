package com.chariotit.uva.sc.qdsl;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.node.*;
import com.chariotit.uva.sc.qdsl.ast.visitor.FormVisitor;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class QLFormBuilder extends JPanel{

    static DefaultFormBuilder builder;
    static JPanel panel;

    public QLFormBuilder() {

        super(new BorderLayout());

        builder = new DefaultFormBuilder(new FormLayout(""));
//        builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");

        panel = builder.getPanel();

        add(panel);

        addQuestion("Dit is een test");


        // addQuestion("Testvraag 123");
        QLFrame f = new QLFrame("Form layout ecxample");

        f.add(this);

        f.pack();

        f.setVisible(true);
    }

    public QLFormBuilder(AstRoot root) {

        super(new BorderLayout());

        List<Form> forms = root.getForms();
        System.out.println("form initialized");

        for (Form form: forms) {
            System.out.println(form.getClass());
            renderForm(form);
        }


        builder = new DefaultFormBuilder(new FormLayout(""));
//        builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");
        builder.appendColumn("5dlu");
        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");

        panel = builder.getPanel();

        add(panel);

        addQuestion("Dit is een test");


        // addQuestion("Testvraag 123");
        QLFrame f = new QLFrame("Form layout ecxample");

        f.add(this);

        f.pack();

        f.setVisible(true);
    }

    private void renderForm(Form form){
        for(FormElement element: form.getFormElements()){
            renderElement(element);
        }
    }

    private void renderElement(FormElement element){
        System.out.println(element.getClass());

        if (element instanceof LineElement) {
            renderLineElement((LineElement) element);
            return;
        }
    }

    private void renderLineElement(LineElement element){
        System.out.println("rendering line element");
        Question question = element.getQuestion();

    }

    private static JComponent componentForType(ExpressionType type){
        switch (type){
            case BOOLEAN:   return new JCheckBox();
            case STRING:    return new JTextField();
            case MONEY:     return currencyTextField();
            case INTEGER:   return numericTextField();
            default:        return new JTextField();
        }
    }

    private static JFormattedTextField currencyTextField() {

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

//        formatter.setOverwriteMode(true);

        // return the text field that is configured by the formatter
        return new JFormattedTextField(formatter);
    }

    private static JFormattedTextField numericTextField() {

        // create a new formatter
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());

        // setup the formatter
        formatter.setValueClass(Integer.class);

        // ensure that overflow is not possible
        formatter.setMinimum(Integer.MIN_VALUE);
        formatter.setMaximum(Integer.MAX_VALUE);

        // no values other than the format are allowed
        formatter.setAllowsInvalid(false);

        // return the text field that is configured by the formatter
        return new JFormattedTextField(formatter);
    }


    // this method should be replaced!
    public static void addQuestion(String question){

        ExpressionType type = ExpressionType.BOOLEAN;
        addQuestion(question, type);

        ExpressionType type1 = ExpressionType.STRING;
        addQuestion(question, type1);

        ExpressionType type2 = ExpressionType.MONEY;
        addQuestion(question, type2);

        ExpressionType type3 = ExpressionType.INTEGER;
        addQuestion(question, type3);

    }

    public static void addQuestion(String question, ExpressionType type){
        builder.append(question, componentForType(type));
        builder.nextLine();

        panel.revalidate();
        panel.repaint();

    }

    public static void addQuestion(LineElement question){
        builder.append(question.getLabel(), componentForType(question.getTypeExpression()));
        builder.nextLine();

        panel.revalidate();
        panel.repaint();
    }


    public void showForm () {


    }
}
