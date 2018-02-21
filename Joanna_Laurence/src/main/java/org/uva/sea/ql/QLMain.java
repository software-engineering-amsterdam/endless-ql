package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.EvaluatorBoolean;
import org.uva.sea.ql.evaluate.EvaluatorDecimal;
import org.uva.sea.ql.evaluate.EvaluatorInteger;

public class QLMain {

    /**
     * Run the application in example.ql
     * @param args
     */
    public static void main(String[] args) {
        QLGui gui = new QLGui();
        gui.addEvaluator("boolean", new EvaluatorBoolean());
        gui.addEvaluator("decimal", new EvaluatorDecimal());
        gui.addEvaluator("integer", new EvaluatorInteger());

        gui.start("/example.ql");
    }
}
