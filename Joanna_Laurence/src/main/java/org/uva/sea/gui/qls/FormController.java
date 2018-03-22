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
    protected Map<String, VBox> createContainer(EvaluationResult evaluationResult) {
        Map<String, VBox> panes = new HashMap<>();
        for (QuestionData questionData : evaluationResult.getQuestions()) {
            Style style = questionData.getStyle();
            if (style != null) {
                String pageName = style.getPage();
                if (!panes.keySet().contains(pageName))
                    panes.put(pageName, this.createTab(pageName));
            }
        }

        return panes;
    }

    private VBox createTab(String tabName) {
        Tab tab = new Tab();
        tab.setText(tabName);

        VBox container = new VBox();
        tab.setContent(container);

        this.tabPane.getTabs().add(tab);
        return container;
    }
}
