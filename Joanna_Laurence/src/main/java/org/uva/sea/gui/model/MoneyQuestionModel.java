package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.MoneyValue;

public class MoneyQuestionModel extends BaseQuestionModel {

    private final MoneyValue value;

    public MoneyQuestionModel(QuestionData data) {
        super(data);
        this.value = (MoneyValue) data.getValue();
    }

    @Override
    public <T> T accept(IQuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return (this.value != null) ? (this.value.getAmount() + " " + this.value.getCurrency()) : "No value";
    }
}
