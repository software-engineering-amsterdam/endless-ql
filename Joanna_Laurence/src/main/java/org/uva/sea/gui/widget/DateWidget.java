package org.uva.sea.gui.widget;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DateValue;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

import java.time.LocalDate;
import java.util.Calendar;

public class DateWidget extends Widget {

    private DateValue widgetValue = new DateValue(Calendar.getInstance());

    public DateWidget(QuestionData questionData) {
        super(questionData);
    }

    @Override
    public boolean updateValue(DateValue dateValue) {
        this.widgetValue = dateValue;
        return true;
    }

    @Override
    public Node convertToGuiNode() {
        DatePicker datePicker = new DatePicker();

        datePicker.getEditor().setText(this.widgetValue.toString());

        datePicker.setOnAction(event -> {
            LocalDate localDate = datePicker.getValue();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
            calendar.set(Calendar.MONTH, localDate.getMonthValue());
            calendar.set(Calendar.YEAR, localDate.getYear());
            Value newValue = new DateValue(calendar);
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), newValue);
        });

        return datePicker;
    }
}
