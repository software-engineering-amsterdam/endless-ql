package gui.widgets;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableMoney;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class MoneyWidget extends TextField implements WidgetInterface {

    private final Question question;

    public MoneyWidget(Question question) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableMoney(null, getText());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.MONEY);
        }
    }

    @Override
    public void setExpression(String value) {
        this.setText(value);
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getMoneyValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.textProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
        });
    }

    @Override
    public void setColor(String color) {
        this.setStyle("-fx-text-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {
        Font currentFont = this.getFont();
        this.setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        Font currentFont = this.getFont();
        this.setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        // TODO fix this
        this.setPrefWidth(width);
    }

    @Override
    public Node getNode() {
        return this;
    }
}
