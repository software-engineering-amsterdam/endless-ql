package org.uva.sea.gui.model;

import org.uva.sea.languages.QlSEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.IOException;

public class ResultController {

    private final QlSEvaluator formGenerator;

    public ResultController(String qlFileName, String qlsFileName) {
        this.formGenerator = new QlSEvaluator(qlFileName, qlsFileName);
    }

    public EvaluationResult getInterpreterResult() throws IOException, InterruptedException {
        return formGenerator.getQuestions();
    }

    public void updateQuestion(String questionName, Value value) {
        formGenerator.setVariable(questionName, value);
    }
}
