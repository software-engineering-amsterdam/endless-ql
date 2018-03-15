package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.ErrorValue;

public class ErrorQuestionModel extends BaseQuestionModel {

    private final ErrorValue value;

    public ErrorQuestionModel(QuestionData data) {
        super(data);
        this.value = (ErrorValue) data.getValue();
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return value != null ? value.getError() : "No value";
    }
}
