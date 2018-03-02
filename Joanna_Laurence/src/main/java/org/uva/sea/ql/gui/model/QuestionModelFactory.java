package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.DataObject.QuestionData;

public interface QuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
