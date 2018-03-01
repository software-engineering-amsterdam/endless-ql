package org.uva.sea.ql.gui;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.model.*;

public class QuestionGuiFactory implements QuestionFactory {

    @Override
    public BaseQuestionRow create(QuestionData data) {
        switch (data.getNodeType()) {
            case STRING:
                return new StringQuestionGUI(data);
            case DECIMAL:
                return new DecimalQuestionGUI(data);
            case DATE:
                return new DateQuestionGUI(data);
            case MONEY:
                return new MoneyQuestionGUI(data);
            case INTEGER:
                return new IntQuestionGUI(data);
            case BOOLEAN:
                return new BooleanQuestionGUI(data);
            case UNKNOWN:
            default:
                return null;
        }
    }
}
