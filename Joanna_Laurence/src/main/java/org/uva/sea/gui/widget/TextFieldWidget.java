package org.uva.sea.gui.widget;


import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.Value;

public class TextFieldWidget implements Widget {

    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        TextField textField = this.createTextField(questionModel);
        textField.focusedProperty().addListener((observable, oldIsFocused, newIsFocused) -> {
            if (!newIsFocused) {
                TextToValueVisitor textToValueVisitor = new TextToValueVisitor(textField.getText());
                Value value = questionModel.accept(textToValueVisitor);

                controller.updateGuiModel(questionModel.getVariableName(), value);
            }
        });
        return textField;
    }

    private TextField createTextField(BaseQuestionModel question) {
        TextField textField = new TextField();
        if (question.getValue() != null) {
            textField.setText(question.displayValue());
        }
        textField.setEditable(true);
        textField.setMinWidth(100.0);

        if (question.isComputed()) {
            textField.setEditable(false);
        }

        return textField;
    }
}
