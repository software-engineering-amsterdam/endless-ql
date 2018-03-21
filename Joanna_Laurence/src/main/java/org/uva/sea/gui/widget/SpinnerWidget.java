package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public class SpinnerWidget extends Widget {

    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public SpinnerWidget(BaseQuestionModel questionModel, FormController controller) {
        super(questionModel, controller);
        this.questionModel = questionModel;
        this.controller = controller;
    }

    @Override
    public Control initialize() {
        //TODO: set generic
        Spinner spinner = new Spinner<>();

        spinner = this.createSpinner(spinner, questionModel.getStyleQLS());

        int initialValue = 3;

        if (questionModel.getValue() != null) {
            System.out.println("Computed boolean value " + questionModel.displayValue());
            initialValue = new IntValue(questionModel.displayValue()).getIntValue();
        }

        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, initialValue);

        // Value factory.
        spinner.setValueFactory(valueFactory);

        //TODO: remove listeners repetitions
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            controller.setLastFocused(questionModel.getVariableName());
            IQuestionModelVisitor<Value> textToValueVisitor = new TextToValueVisitor(newValue.toString());
            Value value = questionModel.accept(textToValueVisitor);
            controller.updateGuiModel(questionModel.getVariableName(), value);
        });

        return spinner;
    }

    //TODO: set font, fontsize and color
    private Spinner createSpinner(Spinner spinner, Style style) {
        if (style != null) {
            if (style.getWidth() != null) {
                spinner.setMinWidth(style.getWidth());
            } else {
                spinner.setMinWidth(Widget.TEXT_WIDTH); //default style
            }
        } else {
            System.out.println("Style is null");
        }
        return spinner;
    }
}
