package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.dataObject.QuestionData;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class QuestionModelFactoryImpl implements QuestionModelFactory {

    @Override
    public BaseQuestionModel create(QuestionData data) {
        switch (data.getNodeType()) {
            case STRING:
                return new StringQuestionModel(data);
            case DECIMAL:
                return new DecimalQuestionModel(data);
            case DATE:
                return new DateQuestionModel(data);
            case MONEY:
                return new MoneyQuestionModel(data);
            case INTEGER:
                return new IntQuestionModel(data);
            case BOOLEAN:
                return new BooleanQuestionModel(data);
            case UNKNOWN:
            default:
                throw new NotImplementedException();
        }
    }
}
