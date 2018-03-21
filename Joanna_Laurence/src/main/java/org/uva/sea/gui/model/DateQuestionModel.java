package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.DateValue;

public class DateQuestionModel extends BaseQuestionModel {

    private final DateValue value;

    public DateQuestionModel(QuestionData data) {
        super(data);
        this.value = (DateValue) data.getValue();
    }

    @Override
    public <T> T accept(IQuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return (this.value != null) ? this.value.getDateValue().toString() : "No value";
    }
}
