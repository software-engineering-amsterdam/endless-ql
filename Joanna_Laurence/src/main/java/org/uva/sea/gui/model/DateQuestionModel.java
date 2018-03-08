package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.DateValue;

public class DateQuestionModel extends BaseQuestionModel {

    private final DateValue value;

    public DateQuestionModel(QuestionData data) {
        super(data);
        this.value = (DateValue) data.getValue();
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return value != null ? value.getDateValue().toString() : "No value";
    }
}
