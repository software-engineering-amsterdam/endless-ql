package org.uva.sea.gui.controller;

import javafx.scene.layout.VBox;
import org.uva.sea.gui.model.factory.PrimaryTypeWidgetFactory;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;

import java.util.HashMap;
import java.util.Map;

public class FormController extends BaseFormController {
    public FormController() {
        super(new PrimaryTypeWidgetFactory());
    }

    protected Map<String, VBox> createContainer(EvaluationResult evaluationResult) {
        HashMap<String, VBox> panes = new HashMap<>();
        panes.put("default", this.container); //TODO
        return panes;
    }
}
