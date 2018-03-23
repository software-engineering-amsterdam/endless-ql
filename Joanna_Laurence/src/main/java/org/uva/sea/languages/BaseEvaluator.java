package org.uva.sea.languages;

import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.io.IOException;

public interface BaseEvaluator {

    EvaluationResult getQuestions() throws IOException, InterruptedException;

    void setVariable(String name, Value value);
}
