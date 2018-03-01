package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.ErrorValue;

public class ErrorQuestionGUI extends BaseQuestionRow {

    private final ErrorValue value;

    public ErrorQuestionGUI(QuestionData data) {
        super(data);
        this.value = (ErrorValue) data.getValue();
    }

    public String getBasicValue(){
        return value.getError();
    }

    @Override
    public void accept(QuestionGuiVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String displayValue() {
        return getBasicValue();
    }
}
