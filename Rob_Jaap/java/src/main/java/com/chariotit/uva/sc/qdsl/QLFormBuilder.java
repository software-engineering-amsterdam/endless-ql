package com.chariotit.uva.sc.qdsl;

import java.awt.*;
import java.util.Set;

import javax.swing.*;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class QLFormBuilder extends JPanel {

    static DefaultFormBuilder builder;

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

        builder.append("First:", new JTextField());

        builder.append("Last:", new JTextField());
        builder.nextLine();

        builder.append("Married:", new JCheckBox());
        builder.nextLine();

        builder.append("Phone:", new JTextField());
        builder.nextLine();

        builder.append("Fax:", new JTextField());
        builder.nextLine();

        builder.append("Email:", new JTextField());
        builder.nextLine();

        builder.appendSeparator("Work");

        builder.append("Company:", new JTextField());
        builder.nextLine();

        builder.append("Phone:", new JTextField());
        builder.nextLine();

        builder.append("Fax:", new JTextField());
        builder.nextLine();

        add(builder.getPanel());

    }

    public static void addQuestion(String question) {

        builder.append(question, new JTextField());
        builder.nextLine();

    }


    public void showForm() {

        QLFrame f = new QLFrame("Form layout ecxample");
        f.setDefaultSize(36);
        f.setDefaultCloseOperation(2);
        f.add(new QLFormBuilder());
        f.pack();
        f.setVisible(true);
    }
}
