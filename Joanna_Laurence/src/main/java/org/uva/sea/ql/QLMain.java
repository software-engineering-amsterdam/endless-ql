package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.EvaluatorBoolean;
import org.uva.sea.ql.evaluate.EvaluatorDecimal;
import org.uva.sea.ql.evaluate.EvaluatorInteger;

public class QLMain {

    /**
     * TODO:
         Bigint << money
         Date with /
         Pass text and do no java logic inside the grammar
         Rename mul add etc
         enum for types
         immutable
         set location <- constructor
         lhs rhs
         rename to visit pattern << doX
         if inside if
         Double dispatch for evaluator
         int/int=decimal
         Add variable value @ eval
         rel: add . add << will return a list not a tree. // a - b - c. << terry group
         // Binary expression - operation
         Remove java in grammar
     */

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
