package gui.model;

import gui.observers.GUIQuestionObserver;
import gui.widgets.Widget;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;

public abstract class GUIQuestion<T> {

    public T value;
    public String name;
    String label;
    Expression condition;
    boolean isComputed;

    Widget widget;
    GUIQuestionObserver observer;

    GUIQuestion(String name, String label, Expression condition, boolean isComputed) {
        this.name = name;
        this.label = label;
        this.condition = condition;
        this.isComputed = isComputed;
    }

    public void setValue(T value) {
        this.value = value;
        this.notifyObserver();
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);
        return interpreterVisitor.visit(this.condition).getBooleanValue();
    }

    private void notifyObserver() {
        if (observer != null) {
            observer.update();
        }
    }

    public void attach(GUIQuestionObserver observer) {
        this.observer = observer;
    }

    public Widget getWidget() {
        return this.widget;
    }

}
