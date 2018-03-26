package org.uva.sea.languages.ql.interpreter.evaluate;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;
import org.uva.sea.languages.ql.interpreter.evaluate.helper.LookupBooleanValue;
import org.uva.sea.languages.ql.parser.elements.IfStatement;
import org.uva.sea.languages.ql.parser.elements.Question;
import org.uva.sea.languages.ql.parser.elements.Statements;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

import java.util.ArrayList;
import java.util.List;

class IfStatementEvaluator extends BaseValueVisitor<Boolean> {

    private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();


    public List<Question> evaluate(IfStatement ifStatement, SymbolTable symbolTable) {
        Value condition = this.expressionEvaluator.evaluate(ifStatement.getExpression(), symbolTable);

        Boolean conditionTrue = condition.accept(new LookupBooleanValue());
        if (conditionTrue == null) {
            return new ArrayList<>();
        }

        Statements execute = conditionTrue ? ifStatement.getThen() : ifStatement.getOtherwise();
        if (execute == null) {
            return new ArrayList<>();
        }

        StatementsEvaluator statementsEvaluator = new StatementsEvaluator(symbolTable);
        return statementsEvaluator.evaluate(execute);
    }
}