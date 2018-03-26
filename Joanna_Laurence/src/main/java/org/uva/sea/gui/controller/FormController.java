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

    protected final Map<String, VBox> createContainers(final EvaluationResult evaluationResult) {
        final Map<String, VBox> tabs = new HashMap<>();
        tabs.put("default", this.container);
        return tabs;
    }
}
