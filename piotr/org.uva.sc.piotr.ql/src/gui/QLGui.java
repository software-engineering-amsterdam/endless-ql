package gui;

import gui.controller.FormController;
import gui.model.FormQuestionHolder;
import gui.view.FormQuestionPanel;
import logic.evaluators.ExpressionEvaluator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QLGui {
    public QLGui(List<FormQuestionHolder> formQuestionHolders, ExpressionEvaluator evaluator) throws Exception {

        JFrame frame = new JFrame("Text Form Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());

        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(1024, 768));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        // form controller setup
        FormController formController = new FormController(formQuestionHolders);

        // render form questions panels
        int i = 0;
        for (FormQuestionHolder formQuestionHolder : formQuestionHolders) {
            gridBagConstraints.gridy = i;
            panel.add(new FormQuestionPanel(formQuestionHolder), gridBagConstraints);
            i++;
        }

        // p.add(submit);
        frame.getContentPane().add(scrollFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
