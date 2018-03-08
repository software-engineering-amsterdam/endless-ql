package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.ErrorValue;
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
    public <T> T accept(QuestionModelVisitor<T> visitor) {
        return visitor.visit(this);
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
