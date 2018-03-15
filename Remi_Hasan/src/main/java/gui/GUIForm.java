package gui;

import javafx.scene.layout.VBox;
import ql.model.Form;
import ql.model.Question;

public class GUIForm extends VBox {

    GUIForm(Form form){
        for(Question question : form.questions){
            this.getChildren().add(new GUIQuestion(question));
        }
    }
}
