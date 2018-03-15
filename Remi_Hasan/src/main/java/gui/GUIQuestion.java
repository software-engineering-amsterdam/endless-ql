package gui;

import gui.widgets.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Question;

public class GUIQuestion extends VBox {

    GUIQuestion(SymbolTable symbolTable, Question question){
        this.getChildren().add(new Label(question.text));
        switch(question.type){
            case INTEGER:
                IntegerWidget integerWidget = new IntegerWidget(question.name);
                integerWidget.textProperty().addListener((ob, oldValue, newValue) -> {
                    symbolTable.setExpression(question.name, integerWidget.getExpression());
                });

                this.getChildren().add(integerWidget);
                break;
            case STRING:
                StringWidget stringWidget = new StringWidget(question.name);
                stringWidget.textProperty().addListener((ob, oldValue, newValue) -> {
                    symbolTable.setExpression(question.name, stringWidget.getExpression());
                });

                this.getChildren().add(stringWidget);
                break;
            case DATE:
                DateWidget dateWidget = new DateWidget(question.name);
                dateWidget.valueProperty().addListener((ob, oldValue, newValue) -> {
                    symbolTable.setExpression(question.name, dateWidget.getExpression());
                });

                this.getChildren().add(dateWidget);
                break;
            case DECIMAL:
                DecimalWidget decimalWidget = new DecimalWidget(question.name);
                decimalWidget.textProperty().addListener((ob, oldValue, newValue) -> {
                    symbolTable.setExpression(question.name, decimalWidget.getExpression());
                });

                this.getChildren().add(decimalWidget);
                break;
            case MONEY:
                MoneyWidget moneyWidget = new MoneyWidget(question.name);
                moneyWidget.textProperty().addListener((ob, oldValue, newValue) -> {
                    symbolTable.setExpression(question.name, moneyWidget.getExpression());
                });

                this.getChildren().add(moneyWidget);
                break;
            case BOOLEAN:
                CheckboxWidget checkboxWidget = new CheckboxWidget(question.name);
                checkboxWidget.selectedProperty().addListener((ob, oldValue, newValue) -> {
                    symbolTable.setExpression(question.name, checkboxWidget.getExpression());
                });

                this.getChildren().add(checkboxWidget);
                break;
            default:
                break;
        }
    }

}
