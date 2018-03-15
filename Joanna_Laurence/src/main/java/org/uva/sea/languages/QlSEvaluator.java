package org.uva.sea.languages;

import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.qls.interpreter.Evaluator;

import java.io.IOException;

public class QlSEvaluator extends QlEvaluator {

    private Evaluator evaluator = new Evaluator();


    private String qlsFileLocation;

    /**
     * Constructor
     *
     * @param qlFileLocation
     * @param qlsFileLocation
     */
    public QlSEvaluator(String qlFileLocation, String qlsFileLocation) {
        super(qlFileLocation);
        this.qlsFileLocation = qlsFileLocation;
    }

    /**
     * Generate EvaluationResult
     *
     * @return EvaluationResult
     * @throws InterruptedException
     */
    @Override
    public EvaluationResult getQuestions() throws IOException, InterruptedException {
        EvaluationResult qlInterpreterResult = super.getQuestions();

        return evaluator.evaluate(this.qlsFileLocation, qlInterpreterResult);
    }
}
