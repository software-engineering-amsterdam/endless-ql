package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public class SpinnerWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: set generic
        Spinner<Integer> spinner = new Spinner<>();

        spinner = setStyle(spinner, questionModel.getStyleQLS());

        final int initialValue = 3;

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new IntegerSpinnerValueFactory(1, 5, initialValue);

        spinner.setValueFactory(valueFactory);

        //TODO: remove listeners repetitions
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            controller.setLastFocused(questionModel.getVariableName());
            TextToValueVisitor textToValueVisitor = new TextToValueVisitor(newValue.toString());
            Value value = questionModel.accept(textToValueVisitor);
            controller.updateGuiModel(questionModel.getVariableName(), value);
        });

        return spinner;
    }

    //TODO: set font, fontsize and color
    private Spinner<Integer> setStyle(Spinner<Integer> spinner, Style style) {
        if (style != null) {
            if (style.getWidth() != null) {
                spinner.setMinWidth(style.getWidth());
            } else {
                System.out.println("Width is null");
            }
        } else {
            System.out.println("Style is null");
        }
        return spinner;
    }
}
