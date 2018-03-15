package gui;

import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import ql.model.Form;
import ql.model.Question;

public class GUIForm extends VBox {

    GUIForm(SymbolTable symbolTable, Form form){
        for(Question question : form.questions){
//            this.getChildren().add(new GUIQuestion(symbolTable, question));
        }
    }
}
