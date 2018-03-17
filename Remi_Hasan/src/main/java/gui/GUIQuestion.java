package gui;

import gui.widgets.*;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Question;

public class GUIQuestion extends VBox {

    GUIQuestion(SymbolTable symbolTable, Question question) {
        Control widget;
        switch (question.type) {
            case INTEGER:
                IntegerWidget integerWidget = new IntegerWidget(question);
                integerWidget.addListeners(symbolTable, question, integerWidget);
                widget = integerWidget;
                break;
            case STRING:
                StringWidget stringWidget = new StringWidget(question);
                stringWidget.addListeners(symbolTable, question, stringWidget);
                widget = stringWidget;
                break;
            case DATE:
                DateWidget dateWidget = new DateWidget(question);
                dateWidget.addListeners(symbolTable, question, dateWidget);
                widget = dateWidget;
                break;
            case DECIMAL:
                DecimalWidget decimalWidget = new DecimalWidget(question);
                decimalWidget.addListeners(symbolTable, question, decimalWidget);
                widget = decimalWidget;
                break;
            case MONEY:
                MoneyWidget moneyWidget = new MoneyWidget(question);
                moneyWidget.addListeners(symbolTable, question, moneyWidget);
                widget = moneyWidget;
                break;
            case BOOLEAN:
                CheckboxWidget checkboxWidget = new CheckboxWidget(question);
                checkboxWidget.addListeners(symbolTable, question, checkboxWidget);
                widget = checkboxWidget;
                break;
            default:
                return;
        }


        Label label = new Label(question.text);
        label.managedProperty().bind(widget.managedProperty());
        label.visibleProperty().bind(widget.visibleProperty());
        this.getChildren().add(label);
        this.getChildren().add(widget);
        this.setPadding(new Insets(20, 20, 20, 20));
    }

}
