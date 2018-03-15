package org.uva.sea.gui.model;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public interface QuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
