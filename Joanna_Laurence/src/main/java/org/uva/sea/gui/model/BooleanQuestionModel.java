package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class BooleanQuestionModel extends BaseQuestionModel {

    private final BooleanValue value;

    public BooleanQuestionModel(QuestionData data) {
        super(data);
        this.value = (BooleanValue) data.getValue();
    }

    public boolean getBasicValue() {
        return value != null && value.getBooleanValue();
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return value != null ? String.valueOf(value.getBooleanValue()) : "No value";
    }
}
