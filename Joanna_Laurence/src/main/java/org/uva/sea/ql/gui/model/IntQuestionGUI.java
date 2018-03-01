package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.IntValue;

public class IntQuestionGUI extends BaseQuestionRow {

    private final IntValue value;

    public IntQuestionGUI(QuestionData data) {
        super(data);
        this.value = (IntValue) data.getValue();
    }

    public int getBasicValue() {
        return value.getIntValue();
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
