package gui;

import ast.model.Form;
import ast.model.statements.Question;
import ast.model.statements.Statement;
import gui.model.FormBlock;
import gui.view.TextForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// http://www.java2s.com/Code/Java/Swing-JFC/Asimplelabelforfieldformpanel.htm
// eventually
// https://www.callicoder.com/javafx-registration-form-gui-tutorial/

public class QLGui {
    public QLGui(List<FormBlock> formBlocks) {

        final TextForm form = new TextForm(formBlocks);

        JButton submit = new JButton("Submit Form");

//        submit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(form.getText(0) + " " + form.getText(1) + ". " + form.getText(2)
//                        + ", age " + form.getText(3));
//            }
//        });

        JFrame f = new JFrame("Text Form Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);
        JPanel p = new JPanel();
        p.add(submit);
        f.getContentPane().add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
