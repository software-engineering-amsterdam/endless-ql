package org.uva.sea.gui.model;

import org.uva.sea.dataObject.QuestionData;

public interface QuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
