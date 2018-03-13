package gui;

import com.google.gson.Gson;
import gui.controller.FormController;
import gui.model.FormQuestionHolder;
import gui.view.FormQuestionPanel;
import logic.evaluators.ExpressionEvaluator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class QLGui {
    public QLGui(String title, List<FormQuestionHolder> formQuestionHolders, ExpressionEvaluator evaluator) throws Exception {

        JFrame frame = new JFrame("QL Form GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridBagLayout());

        TitledBorder titled = new TitledBorder(title);
        panel.setBorder(titled);

        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(400, 600));

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
            String jsonResults = gson.toJson(formController.prepareResults());
            System.out.println(jsonResults);

//            JFileChooser fileChooser = new JFileChooser();
//            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//                File file = fileChooser.getSelectedFile();
//                // save to file
//            }

            final JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(jsonResults);
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //your writing code goes here
            }

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
