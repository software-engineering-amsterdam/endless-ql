package gui.widgets;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class RadioWidget extends HBox implements WidgetInterface {

    private final Question question;
    private final ToggleGroup group;
    private final RadioButton falseButton;
    private final RadioButton trueButton;

    public RadioWidget(Question question, String falseLabel, String trueLabel) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());

        group = new ToggleGroup();

        falseButton = new RadioButton(falseLabel);
        trueButton = new RadioButton(trueLabel);

        falseButton.setSelected(true);

        trueButton.setToggleGroup(group);
        falseButton.setToggleGroup(group);

        this.getChildren().add(falseButton);
        this.getChildren().add(trueButton);
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableBoolean(null, trueButton.isSelected());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.BOOLEAN);
        }
    }

    @Override
    public void setExpression(String value) {
        if(Boolean.parseBoolean(value)){
            trueButton.setSelected(true);
        } else {
            falseButton.setSelected(true);
        }
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getBooleanValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        group.selectedToggleProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
        });
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int fontSize) {

    }

    @Override
    public void setWidth(int width) {

    }
}
