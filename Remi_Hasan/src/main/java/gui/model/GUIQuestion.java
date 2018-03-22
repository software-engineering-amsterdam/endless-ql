package gui.model;

import gui.widgets.*;
import gui.widgets.CheckboxWidget;
import javafx.scene.Parent;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;

public class GUIQuestion {
    public final String identifier;
    public final String label;
    public final ReturnType type;
    public final Expression condition;
    public final boolean computed;

    public GUIQuestion(String identifier, String label, ReturnType type, Expression condition, boolean computed) {
        this.identifier = identifier;
        this.label = label;
        this.type = type;
        this.condition = condition;
        this.computed = computed;
    }

    public GUIWidget render(SymbolTable symbolTable) {
        switch (this.type) {
            case STRING:
                return new TextWidget();
            case INTEGER:
                return new IntegerWidget();
            case DECIMAL:
                return new DecimalWidget();
            case MONEY:
                return new MoneyWidget();
            case DATE:
                return new DateWidget();
            case BOOLEAN:
                return new CheckboxWidget();
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
    }
}
