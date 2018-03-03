package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.value.ErrorValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ErrorQuestionModel extends BaseQuestionModel {

    private final ErrorValue value;

    public ErrorQuestionModel(QuestionData data) {
        super(data);
        this.value = (ErrorValue) data.getValue();
    }

    public String getBasicValue() {
        if (value != null) {
            return value.getError();
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
            return value.getError();
        } else {
            return "No value";
        }
    }
}
