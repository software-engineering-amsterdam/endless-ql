package gui.model;

import gui.widgets.Widget;
import javafx.beans.value.ChangeListener;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;

public abstract class BaseQuestion {

    protected final ChangeListener listener;

    public final String name;
    public String label;
    private Expression condition;
    public boolean isComputed;

    BaseQuestion(String name, String label, Expression condition, boolean isComputed, ChangeListener listener) {
        this.name = name;
        this.label = label;
        this.condition = condition;
        this.isComputed = isComputed;
        this.listener = listener;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);
        return interpreterVisitor.visit(this.condition).getBooleanValue();
    }

    public abstract Widget getWidget();
}
