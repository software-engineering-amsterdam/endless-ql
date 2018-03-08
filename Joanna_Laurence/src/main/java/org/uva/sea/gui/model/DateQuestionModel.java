package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.DateValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;

public class DateQuestionModel extends BaseQuestionModel {

    private final DateValue value;

    public DateQuestionModel(QuestionData data) {
        super(data);
        this.value = (DateValue) data.getValue();
    }

    public Calendar getBasicValue() {
        if (value != null) {
            return value.getDateValue();
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        if (value != null) {
            return value.getDateValue().toString();
        } else {
            return "No value";
        }
    }
}
