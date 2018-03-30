package ql.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ql.QLForm;
import ql.evaluation.value.Value;
import ql.model.Form;
import ql.model.statement.Question;
import ql.visitor.QLVisitor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultsExporter {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public static void export(QLForm qlForm, File exportFile) throws IOException {
        Form form = qlForm.getForm();

        // Evaluate all questions in order
        Map<String, Object> evaluatedQuestions = new LinkedHashMap<>();
        form.accept(new QLVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                Value answer = qlForm.getAnswer(question.getIdentifier());
                evaluatedQuestions.put(question.getIdentifier(), answer.getValue());
                return super.visit(question);
            }
        });

        // Create json and write to file
        FileWriter fileWriter = new FileWriter(exportFile);
        gson.toJson(evaluatedQuestions, fileWriter);
        fileWriter.close();
    }
}
