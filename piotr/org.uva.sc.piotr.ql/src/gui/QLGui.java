package gui;

import ast.model.Form;
import ast.model.statements.Question;
import ast.model.statements.Statement;
import gui.view.TextForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// http://www.java2s.com/Code/Java/Swing-JFC/Asimplelabelforfieldformpanel.htm
// eventually
// https://www.callicoder.com/javafx-registration-form-gui-tutorial/

public class QLGui {
    public QLGui(Form ASTform) {

//        JFrame frame = new JFrame(form.getName());
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        Container contentPane = frame.getContentPane();
//        SpringLayout layout = new SpringLayout();
//        contentPane.setLayout(layout);
//
//        for (Statement statement : form.getStatementList()) {
//            if (statement instanceof Question) {
//                JLabel label = new JLabel(((Question) statement).getLabel());
//                contentPane.add(label);
//                JTextField textField = new JTextField("Text field", 15);
//                label.setLabelFor(textField);
//                contentPane.add(textField);
//            }
//        }
//
//        frame.pack();
//        frame.setVisible(true);

        String[] labels = {"First Name", "Middle Initial", "Last Name", "Age"};
        char[] mnemonics = {'F', 'M', 'L', 'A'};
        int[] widths = {15, 1, 15, 3};
        String[] descs = {"First Name", "Middle Initial", "Last Name", "Age"};

        final TextForm form = new TextForm(labels, mnemonics, widths, descs);

        JButton submit = new JButton("Submit Form");

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
                        + ", age " + form.getText(3));
            }
        });

        JFrame f = new JFrame("Text Form Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);
        JPanel p = new JPanel();
        p.add(submit);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }
}
