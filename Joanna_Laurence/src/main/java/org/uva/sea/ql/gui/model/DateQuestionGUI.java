package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.DateValue;

import java.util.Calendar;

public class DateQuestionGUI extends BaseQuestionRow {

    private final DateValue value;

    public DateQuestionGUI(QuestionData data) {
        super(data);
        this.value = (DateValue) data.getValue();
    }

    public Calendar getBasicValue(){
        return value.getDateValue();
    }

    @Override
    public void accept(QuestionGuiVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return getBasicValue().toString();
    }
}
