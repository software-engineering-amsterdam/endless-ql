package gui.widgets;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableString;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class StringWidget extends TextField implements WidgetInterface {

    private final Question question;

    public StringWidget(Question question) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableString(null, getText());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.STRING);
        }
    }

    @Override
    public void setExpression(String value) {
        this.setText(value);
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
            String text = value.isUndefined() ? "" : value.getStringValue().toString();
            this.setExpression(text);
        });
    }@Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.textProperty().addListener(e -> {
            symbolTable.setExpression(question.name, getExpression(this, question.type));
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
        this.setPrefWidth(width);
    }
}
