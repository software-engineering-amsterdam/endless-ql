package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.DecimalValue;

public class DecimalQuestionGUI extends BaseQuestionRow {

    private final DecimalValue value;

    public DecimalQuestionGUI(QuestionData data) {
        super(data);
        this.value = (DecimalValue) data.getValue();
    }

    public double getBasicValue(){
        return value.getDecimalValue();
    }

    @Override
    public void accept(QuestionGuiVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return String.valueOf(getBasicValue());
    }
}
