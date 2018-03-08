package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;
import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import org.uva.sea.ql.interpreter.evaluate.valueTypes.MoneyValue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;

public class MoneyQuestionModel extends BaseQuestionModel {

    private final MoneyValue value;

    public MoneyQuestionModel(QuestionData data) {
        super(data);
        this.value = (MoneyValue) data.getValue();
    }

    public BigDecimal getBasicValue() {
        if (value != null) {
            return value.getAmount();
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    public String getCurrency() {
        if (value != null) {
            return value.getCurrency();
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
            return String.valueOf(value.getAmount() + " " + value.getCurrency());
        } else {
            return "No value";
        }
    }
}
