package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.value.IntValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class IntQuestionModel extends BaseQuestionModel {

    private final IntValue value;

    public IntQuestionModel(QuestionData data) {
        super(data);
        this.value = (IntValue) data.getValue();
    }

    public int getBasicValue() {
        if (value != null) {
            return value.getIntValue();
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    @Override
    public void accept(QuestionModelVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        if (value != null) {
            return String.valueOf(value.getIntValue());
        } else {
            return "No value";
        }
    }
}
