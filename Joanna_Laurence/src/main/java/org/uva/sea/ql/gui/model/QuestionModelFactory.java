package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.model.BaseQuestionModel;

public interface QuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
