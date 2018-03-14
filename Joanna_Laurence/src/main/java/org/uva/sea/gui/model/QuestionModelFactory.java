package org.uva.sea.gui.model;

import org.uva.sea.ql.interpreter.dataObject.questionData.QuestionData;

public interface QuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
