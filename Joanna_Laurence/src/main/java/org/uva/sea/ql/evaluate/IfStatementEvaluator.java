package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLEvaluator;
import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.IfStatement;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.value.BooleanValue;
import org.uva.sea.ql.value.Value;

import java.util.ArrayList;
import java.util.List;

public class IfStatementEvaluator extends QLValueEvaluator<Boolean> {

    /**
     * Expression evaluator
     */
    private QLEvaluator qlEvaluator = new QLEvaluator();

    /**
     * Evaluates the condition, when true the statements are returned
     * @param ifStatement Statement that is evaluated
     * @param symbolTable Symbol table with data
     * @return List of all seen questions
     */
    public List<Question> evaluate(IfStatement ifStatement, SymbolTable symbolTable) {
        Value condition = this.qlEvaluator.evaluate(ifStatement.getExpression(), symbolTable);

        //Determine condition is true
        Boolean conditionTrue = condition.accept(this);
        if(conditionTrue == null || !conditionTrue) {
            return new ArrayList<>();
        }

        //Get all questions inside if statement
        StatementsEvaluator statementsEvaluator = new StatementsEvaluator(symbolTable);
        return statementsEvaluator.evaluate(ifStatement.getStatements());
    }

    /**
     *
     * @param boolValue
     * @return
     */
    public Boolean visit(BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}