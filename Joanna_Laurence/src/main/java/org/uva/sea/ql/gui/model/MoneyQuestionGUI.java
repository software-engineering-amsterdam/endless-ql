package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.MoneyValue;

import java.math.BigDecimal;

public class MoneyQuestionGUI extends BaseQuestionRow {

    private final MoneyValue value;

    public MoneyQuestionGUI(QuestionData data) {
        super(data);
        this.value = (MoneyValue) data.getValue();
    }

    public BigDecimal getBasicValue(){
        return value.getAmount();
    }

    public String getCurrency(){
        return value.getCurrency();
    }

    @Override
    public void accept(QuestionGuiVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return String.valueOf(getBasicValue() + " " + getCurrency());
    }
}
