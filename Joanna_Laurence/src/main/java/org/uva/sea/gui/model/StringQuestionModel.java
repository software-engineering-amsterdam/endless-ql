package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.StringValue;

public class StringQuestionModel extends BaseQuestionModel {

    private final StringValue value;

    public StringQuestionModel(QuestionData data) {
        super(data);
        this.value = (StringValue) data.getValue();
    }

    public String getBasicValue() {
        if (value != null) {
            return value.getStringValue();
        } else {
            return "No valueTypes";
        }
    }

    @Override
    public void accept(QuestionModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        if (value != null) {
            return value.getStringValue();
        } else {
            return "No valueTypes";
        }
    }
}