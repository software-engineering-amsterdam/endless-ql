package org.uva.sea.gui.controller.utility;

import com.google.gson.GsonBuilder;
import org.uva.sea.gui.model.QuestionModel;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Exporter {

    public final String saveAnswers(final QuestionModel formModel) throws IOException, InterruptedException {
        final File exportFile = FileSelector.getFile("Choose file to save", "JSON", "*.json");
        try (Writer writer = new FileWriter(exportFile.getAbsolutePath())) {
            final String json = new GsonBuilder().create().toJson(this.getQuestionnaireResult(formModel));
            writer.write(json);
        }

        return exportFile.getName();
    }

    private Map<String, String> getQuestionnaireResult(final QuestionModel questionModel) throws IOException, InterruptedException {
        if (questionModel == null) {
            return new HashMap<>();
        }

        final Map<String, String> questionValueMap = new HashMap<>();
        final EvaluationResult evaluationResults = questionModel.getEvaluationResults();

        for (final QuestionData data : evaluationResults.getQuestions()) {
            final String question = data.getLabel();
            final String value = (data.getValue() != null) ? data.getValue().toString() : "null";
            questionValueMap.put(this.makeJsonCompatible(question), this.makeJsonCompatible(value));
        }
        return questionValueMap;
    }

    private String makeJsonCompatible(final String input) {
        return input.replace("\"", "");
    }
}
