package org.uva.sea.ql.gui;

import org.uva.sea.ql.DataObject.QuestionData;
import org.uva.sea.ql.gui.model.BaseQuestionRow;

public interface QuestionFactory {

    BaseQuestionRow create(QuestionData data);
}
