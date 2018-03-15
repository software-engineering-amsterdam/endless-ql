package org.uva.sea.gui.model;

import org.uva.sea.languages.QlsEvaluator;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.evaluate.SymbolTable;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.IOException;

public class GuiModel {

    private final QlsEvaluator formGenerator;

    public GuiModel(String qlFileName, String qlsFileName) {
        this.formGenerator = new QlsEvaluator(qlFileName, qlsFileName);
    }

    public EvaluationResult getInterpreterResult() throws IOException, InterruptedException {
        return formGenerator.getQuestions();
    }

    public void updateQuestion(String questionName, Value value) {
        formGenerator.setVariable(questionName, value);
    }
}
