package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.QuestionGuiVisitor;
import org.uva.sea.ql.value.StringValue;

public class StringQuestionGUI extends BaseQuestionRow {

    private final StringValue value;

    public StringQuestionGUI(QuestionData data) {
        super(data);
        this.value = (StringValue) data.getValue();
    }

    public String getBasicValue(){
        return value.getStringValue();
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
