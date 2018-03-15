package gui;

import gui.widgets.*;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ql.model.Question;

public class GUIQuestion extends VBox {

    GUIQuestion(Question question){
        this.getChildren().add(new Label(question.text));
        switch(question.type){
            case INTEGER:
                this.getChildren().add(new IntegerWidget(question.name));
                break;
            case STRING:
                this.getChildren().add(new StringWidget(question.name));
                break;
            case DATE:
                this.getChildren().add(new DateWidget(question.name));
                break;
            case DECIMAL:
                this.getChildren().add(new DecimalWidget(question.name));
                break;
            case MONEY:
                this.getChildren().add(new MoneyWidget(question.name));
                break;
            case BOOLEAN:
                this.getChildren().add(new RadioWidget(question.name));
                break;
            default:
                break;
        }
    }

}
