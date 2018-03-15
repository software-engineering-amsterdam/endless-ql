package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.IntValue;

public class IntQuestionModel extends BaseQuestionModel {

    private final IntValue value;

    public IntQuestionModel(QuestionData data) {
        super(data);
        this.value = (IntValue) data.getValue();
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return value != null ? String.valueOf(value.getIntValue()) : "No value";
    }
}
