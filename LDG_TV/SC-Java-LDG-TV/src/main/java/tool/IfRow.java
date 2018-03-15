package tool;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;

import java.util.List;

public class IfRow extends Row {

    private CheckBox box;
    private List<Row> body;

    public IfRow(String question, CheckBox box, List<Row> body) {
        super(question, box, false);
        this.box = box;
        this.body = body;
    }

    public BooleanProperty selectedProp() {
        return box.selectedProperty();
    }

    public List<Row> getBody() {
        return body;
    }

    public void setBody(List<Row> body) {
        this.body = body;
    }

    public void setHideQuestions(boolean hide) {
        for (Row r : body){
            r.setHide(hide);
        }
    }
}
