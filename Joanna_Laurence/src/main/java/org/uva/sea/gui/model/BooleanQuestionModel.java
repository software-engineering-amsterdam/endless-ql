package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class BooleanQuestionModel extends BaseQuestionModel {

    private final BooleanValue value;

    public BooleanQuestionModel(QuestionData data) {
        super(data);
        this.value = (BooleanValue) data.getValue();
    }

    public boolean getBasicValue() {
        if (value != null) {
            return value.getBooleanValue();
        } else {
            return false;
        }
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        if (value != null) {
            return String.valueOf(value.getBooleanValue());
        } else {
            return "No value";
        }
    }
}
