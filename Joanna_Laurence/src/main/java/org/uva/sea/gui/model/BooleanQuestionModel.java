package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;

public class BooleanQuestionModel extends BaseQuestionModel {

    private final BooleanValue value;

    public BooleanQuestionModel(QuestionData data) {
        super(data);
        this.value = (BooleanValue) data.getValue();
    }

    public boolean getBasicValue() {
        return (this.value != null) && this.value.getBooleanValue();
    }

    @Override
    public <T> T accept(IQuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return (this.value != null) ? String.valueOf(this.value.getBooleanValue()) : "No value";
    }
}
