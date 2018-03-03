package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.dataObject.QuestionData;

public interface QuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
