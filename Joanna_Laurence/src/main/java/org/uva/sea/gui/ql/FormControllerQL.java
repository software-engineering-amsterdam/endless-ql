package org.uva.sea.gui.ql;

import javafx.scene.layout.Pane;
import org.uva.sea.gui.FormController;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;

import java.util.HashMap;
import java.util.Map;

public class FormControllerQL extends FormController {
    protected Map<String, Pane> createContainer(EvaluationResult evaluationResult) {
        HashMap<String, Pane> panes = new HashMap<>();
        panes.put("default", this.container); //TODO
        return panes;
    }
}
