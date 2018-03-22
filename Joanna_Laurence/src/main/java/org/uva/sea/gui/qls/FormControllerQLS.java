package org.uva.sea.gui.qls;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.uva.sea.gui.FormController;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;

import java.util.HashMap;
import java.util.Map;

public class FormControllerQLS extends FormController {

    @Override
    protected Map<String, VBox> createContainer(EvaluationResult evaluationResult) {
        HashMap<String, VBox> panes = new HashMap<>();
        for (QuestionData questionData : evaluationResult.getQuestions()) {
            Style style = questionData.getStyle();
            if (style != null) {
                String pageName = style.getPage();
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
