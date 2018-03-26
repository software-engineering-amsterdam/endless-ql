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


    public final List<Question> evaluate(final IfStatement ifStatement, final SymbolTable symbolTable) {
        final Value condition = this.expressionEvaluator.evaluate(ifStatement.getExpression(), symbolTable);

        final Boolean conditionTrue = condition.accept(new LookupBooleanValue());
        if (conditionTrue == null) {
            return new ArrayList<>();
        }

        final Statements execute = conditionTrue ? ifStatement.getThenBlock() : ifStatement.getOtherwiseBlock();
        if (execute == null) {
            return new ArrayList<>();
        }

        final StatementsEvaluator statementsEvaluator = new StatementsEvaluator(symbolTable);
        return statementsEvaluator.evaluate(execute);
    }
}