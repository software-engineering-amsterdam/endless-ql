package gui;

import gui.widgets.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;

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
                        integerWidget.setExpression(value.getIntValue().toString());
                    });
                } else {
                    integerWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, integerWidget.getExpression());
                    });
                }

                widget = integerWidget;
                break;
            case STRING:
                StringWidget stringWidget = new StringWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        stringWidget.setExpression(value.getStringValue());
                    });
                } else {
                    stringWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, stringWidget.getExpression());
                    });
                }

                widget = stringWidget;
                break;
            case DATE:
                DateWidget dateWidget = new DateWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        dateWidget.setExpression(value.getDateValue().toString());

                        dateWidget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
                    });
                } else {
                    dateWidget.valueProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, dateWidget.getExpression());
                    });
                }

                widget = dateWidget;
                break;
            case DECIMAL:
                DecimalWidget decimalWidget = new DecimalWidget(question.name);

                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        decimalWidget.setExpression(value.getDecimalValue().toString());

                        decimalWidget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
                    });
                } else {
                    decimalWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, decimalWidget.getExpression());

                        decimalWidget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
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
                        moneyWidget.setExpression(value.getMoneyValue().toString());

                        moneyWidget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
                    });
                } else {
                    moneyWidget.textProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, moneyWidget.getExpression());
                    });
                }

                widget = moneyWidget;
                break;
            case BOOLEAN:
                CheckboxWidget checkboxWidget = new CheckboxWidget(question.name);
                if (question.isComputed()) {
                    symbolTable.addListener(e -> {
                        Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
                        checkboxWidget.setExpression(value.getBooleanValue().toString());

                        checkboxWidget.setVisible(expressionEvaluator.visit(question.condition).getBooleanValue());
                    });
                } else {
                    checkboxWidget.selectedProperty().addListener(e -> {
                        symbolTable.setExpression(question.name, checkboxWidget.getExpression());
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

}
