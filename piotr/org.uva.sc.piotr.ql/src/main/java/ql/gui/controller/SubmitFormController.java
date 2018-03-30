package ql.gui.controller;

import com.google.gson.Gson;
import ql.gui.model.FormModel;
import ql.gui.model.QuestionModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class SubmitFormController implements ActionListener {

    private FormModel formModel;

    public SubmitFormController(FormModel formModel) {
        this.formModel = formModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Gson gson = new Gson();
        String jsonResults = gson.toJson(this.prepareResults());

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setSelectedFile(new File("results.json"));
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
        }
    }

    private LinkedHashMap<String, Object> prepareResults() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        for (QuestionModel questionModel : this.formModel.getQuestionModels()) {
            if (questionModel.getVisibility()) {
                result.put(questionModel.getVariableName(), questionModel.getJavaTypedValue());
            }
        }
        return result;
    }
}
