package gui.model;

import gui.widgets.Widget;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;

public abstract class BaseQuestion {
    public String label;
    private Expression condition;
    boolean isComputed;

    BaseQuestion(String label, Expression condition, boolean isComputed) {
        this.label = label;
        this.condition = condition;
        this.isComputed = isComputed;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);
        return interpreterVisitor.visit(this.condition).getBooleanValue();
    }

    public abstract Widget getWidget();
}
