package org.uva.sea.languages;

import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.qls.interpreter.Evaluator;

import java.io.IOException;

public class QlSEvaluator extends QlEvaluator {

    private final Evaluator evaluator = new Evaluator();

    private final String qlsFileLocation;


    public QlSEvaluator(final String qlFileLocation, final String qlsFileLocation) {
        super(qlFileLocation);
        this.qlsFileLocation = qlsFileLocation;
    }

    @Override
    public final EvaluationResult evaluate() throws IOException, InterruptedException {
        final EvaluationResult qlInterpreterResult = super.evaluate();
        if (qlInterpreterResult.getAst() == null)
            return qlInterpreterResult;

        return this.evaluator.evaluate(this.qlsFileLocation, qlInterpreterResult);
    }
}
