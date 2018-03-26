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

    public DateWidget(final QuestionData questionData) {
        super(questionData);
    }

    @Override
    public final boolean updateValue(final DateValue dateValue) {
        this.widgetValue = dateValue;
        return true;
    }

    @Override
    public final Node convertToGuiNode() {
        final DatePicker datePicker = new DatePicker();

        datePicker.getEditor().setText(this.widgetValue.toString());

        datePicker.setOnAction(event -> {
            final LocalDate localDate = datePicker.getValue();
            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
            calendar.set(Calendar.MONTH, localDate.getMonthValue());
            calendar.set(Calendar.YEAR, localDate.getYear());
            final Value newValue = new DateValue(calendar);
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), newValue);
        });

        return datePicker;
    }
}
