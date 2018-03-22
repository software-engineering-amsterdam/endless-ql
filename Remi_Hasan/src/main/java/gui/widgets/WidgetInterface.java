package gui.widgets;

import javafx.scene.Node;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableUndefined;

public interface WidgetInterface {
    Expression getExpression();

    void setExpression(String value);

    default void addListeners(SymbolTable symbolTable, Question question, WidgetInterface widget) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);
        if (question.isComputed()) {
            addComputedListener(symbolTable, expressionEvaluator);
        } else {
            addNonComputedListener(symbolTable);
        }

        addVisibilityListener(symbolTable, question, widget, expressionEvaluator);
    }

    void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator);

    void addNonComputedListener(SymbolTable symbolTable);

    default void addVisibilityListener(SymbolTable symbolTable, Question question, WidgetInterface widget, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            widget.getNode().setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
        });

        widget.getNode().setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
    }


    default Expression getExpression(WidgetInterface widget, ReturnType returnType) {
        try {
            return widget.getExpression();
        } catch (Exception e) {
            return new ExpressionVariableUndefined(null, returnType);
        }
    }

    // StyleAttribute
    public void setColor(String color);
    public void setFont(String font);
    public void setFontSize(int fontSize);
    public void setWidth(int width);

    public Node getNode();
}
