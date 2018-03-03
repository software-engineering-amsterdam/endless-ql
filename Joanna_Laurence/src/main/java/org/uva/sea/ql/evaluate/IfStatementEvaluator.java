package org.uva.sea.ql.evaluate;

import org.uva.sea.ql.evaluate.valueTypes.BooleanValue;
import org.uva.sea.ql.evaluate.valueTypes.Value;
import org.uva.sea.ql.parser.elements.IfStatement;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.Statements;
import org.uva.sea.ql.visitor.BaseValueVisitor;

import java.util.ArrayList;
import java.util.List;

public class IfStatementEvaluator extends BaseValueVisitor<Boolean> {

    /**
     * Expression evaluator
     */
    private ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    /**
     * Evaluates the condition, when true the statements are returned
     *
     * @param ifStatement Statement that is evaluated
     * @param symbolTable Symbol table with helpers
     * @return List of all seen questions
     */
    public List<Question> evaluate(IfStatement ifStatement, SymbolTable symbolTable) {
        Value condition = this.expressionEvaluator.evaluate(ifStatement.getExpression(), symbolTable);

        //Determine condition is true
        Boolean conditionTrue = condition.accept(this);
        if (conditionTrue == null) {
            return new ArrayList<>();
        }

        //Get all questions inside the targeted block
        Statements execute = conditionTrue ? ifStatement.getThen() : ifStatement.getOtherwise();
        if (execute == null) {
            return new ArrayList<>();
        }

        StatementsEvaluator statementsEvaluator = new StatementsEvaluator(symbolTable);
        return statementsEvaluator.evaluate(execute);
    }

    /**
     * @param boolValue
     * @return
     */
    public Boolean visit(BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}