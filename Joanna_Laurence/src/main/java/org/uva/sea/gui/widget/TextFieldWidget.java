package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.uva.sea.gui.newImpl.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public class TextFieldWidget extends Widget {


    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public TextFieldWidget(BaseQuestionModel questionModel, FormController controller) {
        super(questionModel, controller);
        this.questionModel = questionModel;
        this.controller = controller;
    }

    @Override
    public Control initialize() {
        TextField textField = this.createTextField(this.questionModel);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.controller.setLastFocused(this.questionModel.getVariableName());
            System.out.println("TextField Text Changed (newValue: " + newValue + ')');
            IQuestionModelVisitor<Value> textToValueVisitor = new TextToValueVisitor(newValue);
            Value value = this.questionModel.accept(textToValueVisitor);
            this.controller.updateGuiModel(this.questionModel.getVariableName(), value);
        });
        textField.positionCaret(textField.getText().length());
        return textField;
    }

    private TextField createTextField(BaseQuestionModel question) {
        TextField textField = new TextField();

        textField = this.setStyle(textField, question.getStyleQLS());

        if (question.getValue() != null) {
            textField.setText(question.displayValue());
        }
        textField.setEditable(true);
        textField.setMinWidth(Widget.TEXT_WIDTH);

        if (question.isComputed()) {
            textField.setEditable(false);
        }

        //text validation
//        TextFormatter textFormatter = questionModel.accept(new TextFormatterVisitor());
//        textField.setTextFormatter(textFormatter);

        return textField;
    }

    //TODO: set color from styleQLS
    private TextField setStyle(TextField textField, Style style) {
        if (style != null) {
            if (style.getWidth() != null) {
                textField.setMinWidth(style.getWidth());
            }
            if ((style.getFont() != null) && (style.getFontSize() != null)) {
                textField.setFont(new Font(style.getFont(), style.getFontSize()));
            }
        } else {
            System.out.println("Style is null");
        }
        return textField;
    }
}
