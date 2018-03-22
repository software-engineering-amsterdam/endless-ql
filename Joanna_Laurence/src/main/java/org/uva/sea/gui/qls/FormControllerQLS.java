package org.uva.sea.gui.qls;

import javafx.scene.layout.Pane;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.ql.FormControllerQL;
import org.uva.sea.languages.ql.interpreter.dataObject.EvaluationResult;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;

import java.util.HashMap;
import java.util.Map;

public class FormControllerQLS extends FormController {

    @Override
    protected Map<String, Pane> createContainer(EvaluationResult evaluationResult) {
        HashMap<String, Pane> panes = new HashMap<>();
        for (QuestionData questionData : evaluationResult.getQuestions()) {
            Style style = questionData.getStyle();
            if (style != null)
                panes.put("default", new Pane()); //TODO
        }

        return panes;
    }
}
