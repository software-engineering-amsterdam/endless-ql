package gui;

import gui.view.FormPanel;
import logic.evaluators.ExpressionEvaluator;
import gui.model.FormQuestion;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// http://www.java2s.com/Code/Java/Swing-JFC/Asimplelabelforfieldformpanel.htm
// eventually
// https://www.callicoder.com/javafx-registration-form-gui-tutorial/

public class QLGui {
    public QLGui(List<FormQuestion> formQuestions, ExpressionEvaluator evaluator) {

//        final TextForm form = new TextForm(formQuestions, evaluator);
//
//        JButton submit = new JButton("Submit Form");
//
//        submit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(formQuestions.toString());
//            }
//        });

        JFrame frame = new JFrame("Text Form Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());

        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1024, 768));

//        JButton button1 = new JButton("hide me");
//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                button1.setVisible(false);
//                frame.pack();
//            }
//        });
//
//        JButton button2 = new JButton("toggle button 1");
//        button2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                button1.setVisible(!button1.isVisible());
//                frame.pack();
//            }
//        });

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = GridBagConstraints.WEST;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        int i = 0;
        for (FormQuestion question : formQuestions) {
            gridBagConstraints.gridy = i;
            panel.add(new FormPanel(question), gridBagConstraints);
            i++;
        }


//        gridBagConstraints.gridy = 0;
//        panel.add(button1, gridBagConstraints);
//
//        gridBagConstraints.gridy = 1;
//        panel.add(button2, gridBagConstraints);

//        gridBagConstraints.gridy = 0;
//        panel.add(new FormComponent("What is your name?"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 1;
//        panel.add(new FormComponent("How old?"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 2;
//        panel.add(new JButton("hello"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 3;
//        panel.add(new JButton("hello"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 4;
//        panel.add(new JButton("hello"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 5;
//        panel.add(new JButton("hello"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 6;
//        panel.add(new JButton("hello"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 7;
//        panel.add(new JButton("hello"), gridBagConstraints);
//
//        gridBagConstraints.gridy = 8;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 9;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 10;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 11;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 12;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 13;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 14;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 15;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 16;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 17;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 18;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 19;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 20;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 21;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 22;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 23;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 24;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 25;
//        panel.add(new JButton("hello"), gridBagConstraints);
//        gridBagConstraints.gridy = 26;
//        panel.add(new JButton("hello"), gridBagConstraints);

        // p.add(submit);
        frame.getContentPane().add(scrollFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
