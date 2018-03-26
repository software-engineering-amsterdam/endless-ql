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
    protected final Map<String, VBox> createContainers(final EvaluationResult evaluationResult) {
        final Map<String, VBox> containers = new HashMap<>();
        for (final QuestionData questionData : evaluationResult.getQuestions()) {
            final Style style = questionData.getStyle();
            if (style == null)
                continue;

            final String containerName = style.getPage();
            if (!containers.keySet().contains(containerName))
                containers.put(containerName, this.createContainerTab(containerName));
        }

        return containers;
    }

    private VBox createContainerTab(final String tabName) {
        final Tab tab = new Tab();
        tab.setText(tabName);

        final VBox container = new VBox();
        tab.setContent(container);

        this.tabPane.getTabs().add(tab);
        return container;
    }
}
