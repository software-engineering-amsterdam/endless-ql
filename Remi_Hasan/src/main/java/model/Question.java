package model;

import analysis.SymbolTable;
import evaluation.ExpressionEvaluator;
import model.expression.Expression;
import model.expression.ReturnType;

public class Question {

    public final ReturnType type;
    public final String name;
    public final String text;
    public final Expression defaultAnswer;
    public final Expression condition;
    private final boolean isEditable;

    public Question(ReturnType type, String name, String text, Expression defaultAnswer, boolean isEditable, Expression condition) {
        this.type = type;
        this.name = name;
        this.text = text;
        this.defaultAnswer = defaultAnswer;
        this.isEditable = isEditable;
        this.condition = condition;
    }

    public boolean isVisible(SymbolTable symbolTable) {
        ExpressionEvaluator interpreterVisitor = new ExpressionEvaluator(symbolTable);
        return interpreterVisitor.visit(this.condition).getBooleanValue();
    }

    public boolean isEditable() {
        return isEditable;
    }
}
