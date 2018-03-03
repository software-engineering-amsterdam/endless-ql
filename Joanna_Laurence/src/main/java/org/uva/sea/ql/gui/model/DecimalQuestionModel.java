package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.dataObject.QuestionData;
import org.uva.sea.ql.value.DecimalValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DecimalQuestionModel extends BaseQuestionModel {

    private final DecimalValue value;

    public DecimalQuestionModel(QuestionData data) {
        super(data);
        this.value = (DecimalValue) data.getValue();
    }

    public double getBasicValue() {
        if (value != null) {
            return value.getDecimalValue();
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
            return String.valueOf(value.getDecimalValue());
        } else {
            return "No value";
        }
    }
}
