package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.value.StringValue;

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
            return "No value";
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
            return "No value";
        }
    }
}
