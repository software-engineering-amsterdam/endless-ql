package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.Style;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DecimalValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.StringValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.lang.reflect.InvocationTargetException;

public class TextFieldWidget extends Widget {

    private Value widgetValue = new StringValue("");

    public TextFieldWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(DecimalValue decimalValue) {
        this.widgetValue = decimalValue;
        return true;
    }

    @Override
    public boolean updateValue(IntValue intValue) {
        this.widgetValue = intValue;
        return true;
    }

    @Override
    public boolean updateValue(StringValue stringValue) {
        this.widgetValue = stringValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {

        TextField textField = new TextField();
        textField.setText(this.widgetValue.toString());
        textField.setEditable(true);
        textField.setMinWidth(BaseRenderable.TEXT_WIDTH);

        if (this.questionData.isComputed()) {
            textField.setEditable(false);
        }

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            Value newWidgetValue;
            try {
                newWidgetValue = this.widgetValue.getClass().getDeclaredConstructor(String.class).newInstance(newValue);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ignored) {
                newWidgetValue = this.widgetValue;
            }
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), newWidgetValue);
        });

        textField.positionCaret(textField.getText().length());
        return textField;
    }

}
