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

        LocalDate localDate = convertToLocalDate(this.widgetValue.toString());
        if(localDate != null){
            datePicker.setValue(localDate);
        }

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.sendUpdateValueEvent(this.questionData.getQuestionName(), convertToDateValue(newValue));
        });

        return datePicker;
    }

    private LocalDate convertToLocalDate(String date) {
        String dates[] = date.split("/");
        if(dates.length != 3)
            return null;
        return LocalDate.of(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), Integer.parseInt(dates[0]));
    }

    private Value convertToDateValue(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
        calendar.set(Calendar.MONTH, localDate.getMonthValue());
        calendar.set(Calendar.YEAR, localDate.getYear());
        return new DateValue(calendar);
    }
}
