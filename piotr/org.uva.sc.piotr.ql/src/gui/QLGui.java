package gui;

import com.google.gson.Gson;
import gui.controller.FormController;
import gui.model.FormQuestionHolder;
import gui.view.FormQuestionPanel;
import logic.evaluators.ExpressionEvaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QLGui {
    public QLGui(List<FormQuestionHolder> formQuestionHolders, ExpressionEvaluator evaluator) throws Exception {

        JFrame frame = new JFrame("Text Form Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());

        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(800, 600));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        // form controller setup
        FormController formController = new FormController(formQuestionHolders, evaluator);

        // render form questions panels
        int i = 0;
        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {
            gridBagConstraints.gridy = i;
            panel.add(new FormQuestionPanel(formQuestionHolder), gridBagConstraints);
            i++;
        }

        JButton submit = new JButton("Submit form");
        submit.addActionListener(e -> {
            System.out.println("Submitted");
            Gson gson = new Gson();
            System.out.println(gson.toJson(formController.prepareResults()));
        });

        gridBagConstraints.gridy = i;
        panel.add(submit, gridBagConstraints);
        frame.getContentPane().add(scrollFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
