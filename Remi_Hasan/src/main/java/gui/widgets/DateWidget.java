package gui.widgets;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableDate;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.time.LocalDate;

public class DateWidget extends DatePicker implements WidgetInterface {

    private final Question question;

    public DateWidget(Question question) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableDate(null, this.valueProperty().getValue());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.DATE);
        }
    }

    @Override
    public void setExpression(String value) {
        this.valueProperty().setValue(LocalDate.parse(value));
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getDateValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.valueProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
        });
    }


    @Override
    public void setColor(String color) {
        this.setStyle("-fx-text-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {
        Font currentFont = this.getEditor().getFont();
        this.getEditor().setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        Font currentFont = this.getEditor().getFont();
        this.getEditor().setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        this.setPrefWidth(width);
    }

    @Override
    public Node getNode() {
        return this;
    }
}
