package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.Slider;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public class SliderWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: add event handler to Slider, get properties from Widget

        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(10);

        //TODO: remove listeners repetitions
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            controller.setLastFocused(questionModel.getVariableName());
            QuestionModelVisitor<Value> textToValueVisitor = new TextToValueVisitor(String.valueOf(newValue));
            Value value = questionModel.accept(textToValueVisitor);
            controller.updateGuiModel(questionModel.getVariableName(), value);
        });

        return slider;
    }
}
