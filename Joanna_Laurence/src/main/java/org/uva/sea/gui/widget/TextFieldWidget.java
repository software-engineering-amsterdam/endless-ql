package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.render.visitor.TextToValueVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.util.function.UnaryOperator;

public class TextFieldWidget implements Widget {

    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        TextField textField = this.createTextField(questionModel);

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            controller.setLastFocused(questionModel.getVariableName());
            System.out.println("TextField Text Changed (newValue: " + newValue + ")");
            TextToValueVisitor textToValueVisitor = new TextToValueVisitor(newValue);
            Value value = questionModel.accept(textToValueVisitor);
            controller.updateGuiModel(questionModel.getVariableName(), value);
        });
        textField.positionCaret(textField.getText().length());
        return textField;
    }

    private TextField createTextField(BaseQuestionModel question) {
        TextField textField = new TextField();

        textField = setStyle(textField, question.getStyleQLS());

        if (question.getValue() != null) {
            textField.setText(question.displayValue());
        }
        textField.setEditable(true);
        textField.setMinWidth(100.0);

        if (question.isComputed()) {
            textField.setEditable(false);
        }

        textField = validateInput(textField, question.getWidgetType());

        return textField;
    }

    private TextField validateInput(TextField textField, WidgetType widgetType) {

        switch (widgetType) {
            case MONEY_DOLLAR:
            case MONEY_EURO:
            case INTEGER:
                StringConverter<Integer> formatterInteger = new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object != null ? object.toString() : "0";
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                };

                textField.setTextFormatter(new TextFormatter<Integer>(formatterInteger));
            case DECIMAL:
                StringConverter<Double> formatterDecimal = new StringConverter<Double>() {
                    @Override
                    public String toString(Double object) {
                        return object != null ? object.toString() : "0";
                    }

                    @Override
                    public Double fromString(String string) {
                        return Double.parseDouble(string);
                    }
                };

                textField.setTextFormatter(new TextFormatter<Double>(formatterDecimal));
            case STRING:

                textField.setTextFormatter(new TextFormatter<Object>(new UnaryOperator<TextFormatter.Change>() {
                    @Override
                    public TextFormatter.Change apply(TextFormatter.Change change) {
                        return null;
                    }
                }));
            default:
        }
        return textField;
    }

    //TODO: set color from styleQLS
    private TextField setStyle(TextField textField, Style style) {
        if (style != null) {
            if (style.getWidth() != null) {
                textField.setPrefWidth(style.getWidth());
            }
            if (style.getFont() != null && style.getFontSize() != null) {
                textField.setFont(new Font(style.getFont(), style.getFontSize()));
            }
        } else {
            System.out.println("Style is null");
        }
        return textField;
    }
}
