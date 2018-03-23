package org.uva.sea.gui.qls;

import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.controller.BaseFormController;
import org.uva.sea.gui.qls.factory.WidgetFactory;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;

import java.util.HashMap;
import java.util.Map;

public class FormController extends BaseFormController {

    public FormController() {
        super(new WidgetFactory());
    }

    @Override
    protected Map<String, VBox> createContainers(EvaluationResult evaluationResult) {
        Map<String, VBox> containers = new HashMap<>();
        for (QuestionData questionData : evaluationResult.getQuestions()) {
            Style style = questionData.getStyle();
            if (style == null)
                continue;

            String containerName = style.getPage();
            if(!containers.keySet().contains(containerName))
                containers.put(containerName, this.createContainerTab(containerName));
        }

        return containers;
    }

    private VBox createContainerTab(String tabName) {
        Tab tab = new Tab();
        tab.setText(tabName);

        VBox container = new VBox();
        tab.setContent(container);

        this.tabPane.getTabs().add(tab);
        return container;
    }
}
