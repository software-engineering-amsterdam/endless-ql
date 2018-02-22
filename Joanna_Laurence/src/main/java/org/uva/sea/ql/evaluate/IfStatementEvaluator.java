package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.QLEvaluator;
import org.uva.sea.ql.QLValueEvaluator;
import org.uva.sea.ql.parser.elements.IfStatement;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.value.BooleanValue;
import org.uva.sea.ql.value.Value;

import java.util.ArrayList;
import java.util.List;

public class IfStatementEvaluator extends QLValueEvaluator<List<Question>> {

    /**
     *
     */
    private StatementsEvaluator statementsEvaluator;

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
        this.statementsEvaluator = new StatementsEvaluator(ifStatement.getStatements());

        //Visit the condition that returns the list of questions when they should be visible
        Value condition = this.qlEvaluator.evaluate(ifStatement.getExpression(), symbolTable);
        return condition.accept(this);
    }

    /**
     *
     * @param boolValue
     * @return
     */
    public List<Question> visit(BooleanValue boolValue) {
        if(boolValue.getBooleanValue()) {
            return this.statementsEvaluator.evaluate();
        }

        return new ArrayList<>();
    }
}