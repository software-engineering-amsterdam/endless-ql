package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.StringValue;

public class StringQuestionModel extends BaseQuestionModel {

    private final StringValue value;
//TODO: REMOVE CAST
    public StringQuestionModel(QuestionData data) {
        super(data);
        this.value = (StringValue) data.getValue();
    }

    @Override
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return (this.value != null) ? this.value.getStringValue() : "No value";
    }
}
