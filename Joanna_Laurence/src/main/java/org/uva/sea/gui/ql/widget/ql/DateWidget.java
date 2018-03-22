package org.uva.sea.gui.ql.widget.ql;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import org.uva.sea.gui.ql.widget.QLWidget;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DateValue;

import java.time.LocalDate;
import java.util.Calendar;

public class DateWidget extends QLWidget {
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

        datePicker.setOnAction(event -> {
            LocalDate date = datePicker.getValue();
            System.out.println("Selected date: " + date);
        });

        return this.drawComponent(this.questionData.getLabel(), datePicker);
    }
}
