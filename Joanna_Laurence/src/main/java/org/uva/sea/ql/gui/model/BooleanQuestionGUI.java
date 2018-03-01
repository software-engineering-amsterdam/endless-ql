package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.BooleanValue;

public class BooleanQuestionGUI extends BaseQuestionRow {

    private final BooleanValue booleanValue;

    public BooleanQuestionGUI(QuestionData data) {
        super(data);
        this.booleanValue = (BooleanValue) data.getValue();
    }

    public boolean getBasicValue() {
        return booleanValue.getBooleanValue();
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
