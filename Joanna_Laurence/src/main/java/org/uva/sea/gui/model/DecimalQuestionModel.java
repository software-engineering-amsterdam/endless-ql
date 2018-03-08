package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.DecimalValue;

public class DecimalQuestionModel extends BaseQuestionModel {

    private final DecimalValue value;

    public DecimalQuestionModel(QuestionData data) {
        super(data);
        this.value = (DecimalValue) data.getValue();
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return value != null ? String.valueOf(value.getDecimalValue()) : "No value";
    }
}
