package gui.model;

import gui.widgets.*;
import gui.widgets.CheckboxWidget;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

    public Parent render(SymbolTable symbolTable) {
        VBox vBox = new VBox();
        Label questionLabel = new Label(label);
        vBox.getChildren().add(questionLabel);
        switch (this.type) {
            case STRING:
                vBox.getChildren().add(new TextWidget());
                break;
            case INTEGER:
                vBox.getChildren().add(new IntegerWidget());
                break;
            case DECIMAL:
                vBox.getChildren().add(new DecimalWidget());
                break;
            case MONEY:
                vBox.getChildren().add(new MoneyWidget());
                break;
            case DATE:
                vBox.getChildren().add(new DateWidget());
                break;
            case BOOLEAN:
                vBox.getChildren().add(new CheckboxWidget());
                break;
            default:
                throw new UnsupportedOperationException("Question type not implemented to render in GUI");
        }
        return vBox;
    }
}
