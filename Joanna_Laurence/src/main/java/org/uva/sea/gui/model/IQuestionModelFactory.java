package org.uva.sea.gui.model;

import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QuestionData;

public interface IQuestionModelFactory {

    BaseQuestionModel create(QuestionData data);
}
