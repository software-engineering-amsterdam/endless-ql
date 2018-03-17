package gui;

import gui.widgets.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class GUIQuestion extends VBox {

    GUIQuestion(SymbolTable symbolTable, Question question) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(symbolTable);

        Node widget;
        switch (question.type) {
            case INTEGER:
                IntegerWidget integerWidget = new IntegerWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        String text = value.isUndefined() ? "" : value.getIntValue().toString();
                        integerWidget.setExpression(text);
                    });
                } else {
                    integerWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, getExpression(integerWidget, question.type));
                    });
                }

                widget = integerWidget;
                break;
            case STRING:
                StringWidget stringWidget = new StringWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        String text = value.isUndefined() ? "" : value.getStringValue().toString();
                        stringWidget.setExpression(text);
                    });
                } else {
                    stringWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, getExpression(stringWidget, question.type));
                    });
                }

                widget = stringWidget;
                break;
            case DATE:
                DateWidget dateWidget = new DateWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        String text = value.isUndefined() ? "" : value.getDateValue().toString();
                        dateWidget.setExpression(text);
                    });
                } else {
                    dateWidget.valueProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, getExpression(dateWidget, question.type));
                    });
                }

                widget = dateWidget;
                break;
            case DECIMAL:
                DecimalWidget decimalWidget = new DecimalWidget(question.name);

                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        String text = value.isUndefined() ? "" : value.getDecimalValue().toString();
                        decimalWidget.setExpression(text);
                    });
                } else {
                    decimalWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, getExpression(decimalWidget, question.type));
                    });
                }

                decimalWidget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());

                widget = decimalWidget;
                break;
            case MONEY:
                MoneyWidget moneyWidget = new MoneyWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        String text = value.isUndefined() ? "" : value.getMoneyValue().toString();
                        moneyWidget.setExpression(text);
                    });
                } else {
                    moneyWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, getExpression(moneyWidget, question.type));
                    });
                }

                widget = moneyWidget;
                break;
            case BOOLEAN:
                CheckboxWidget checkboxWidget = new CheckboxWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        String text = value.isUndefined() ? "" : value.getBooleanValue().toString();
                        checkboxWidget.setExpression(text);
                    });
                } else {
                    checkboxWidget.selectedProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, getExpression(checkboxWidget, question.type));
                    });
                }

                widget = checkboxWidget;
                break;
            default:
                return;
        }
        symbolTable.addListener(e -> {
            widget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
        });


        Label label = new Label(question.text);
        label.managedProperty().bind(widget.managedProperty());
        label.visibleProperty().bind(widget.visibleProperty());
        this.getChildren().add(label);
        this.getChildren().add(widget);
    }

    private Expression getExpression(WidgetInterface widget, ReturnType returnType){
        try{
            return widget.getExpression();
        } catch(Exception e){
            return new ExpressionVariableUndefined(null, returnType);
        }
    }

}
