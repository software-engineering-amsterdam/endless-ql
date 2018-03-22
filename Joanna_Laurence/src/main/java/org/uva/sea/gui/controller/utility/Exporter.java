package org.uva.sea.gui.controller.utility;

import com.google.gson.GsonBuilder;
import org.uva.sea.gui.model.QuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class Exporter {

    public String saveAnswers(QuestionModel formModel) throws IOException, InterruptedException {
        File file = new FileSelector("Choose file to save", "JSON", "*.json").getFile();
        Writer writer = new FileWriter(file.getAbsolutePath());
        String json = new GsonBuilder().create().toJson(this.createObjectToSave(formModel));
        writer.write(json);
        writer.close();
        return file.getName();
    }

    /**
     * Create a HashMap with given answers to questions.
     *
     * @param questionModel questions to save
     * @return HashMap with key as a question label and value as an answer
     */
    private HashMap<String, String> createObjectToSave(QuestionModel questionModel) throws IOException, InterruptedException {
        HashMap<String, String> hashMap = new HashMap<>();

        if (questionModel == null)
            return hashMap;

        for (QuestionData questionData : questionModel.getEvaluationResults().getQuestions()) {
            String value;
            if (questionData.getValue() == null) {
                value = "null";
            } else {
                value = questionData.getValue().toString();
            }
            hashMap.put(questionData.getLabel().replace("\"", ""), value.replace("\"", ""));
        }
        return hashMap;
    }
}
