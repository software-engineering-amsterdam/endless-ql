package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.EvaluatorBoolean;
import org.uva.sea.ql.evaluate.EvaluatorDecimal;
import org.uva.sea.ql.evaluate.EvaluatorInteger;
import org.uva.sea.ql.parser.NodeType;

public class QLMain {

    /**
     * TODO:
         Date with /
         Rename mul add etc
         set location <- constructor
         lhs rhs
         rename to visit pattern << doX to visit

         Double dispatch for evaluator?
         int/int=decimal when needed
         rel: add . add << will return a list not a tree. // a - b - c. << terry group
         // Binary expression - operation as name? Instead of condition and logical? logical = binary operator
         Support defining values to variables. See todo @ evaluator
     */

    /**
     * Run the application in example.ql
     * @param args
     */
    public static void main(String[] args) {
        QLGui gui = new QLGui();
        gui.addEvaluator(NodeType.BOOLEAN, new EvaluatorBoolean());
        gui.addEvaluator(NodeType.DECIMAL, new EvaluatorDecimal());
        gui.addEvaluator(NodeType.INTEGER, new EvaluatorInteger());

        gui.start("/example.ql");
    }
}
