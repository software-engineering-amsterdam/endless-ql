package org.uva.sea.ql.gui.renderer;

import org.uva.sea.ql.gui.model.BaseQuestionModel;

@FunctionalInterface
public interface QuestionRenderer {

    void render(BaseQuestionModel questionRow);
}
