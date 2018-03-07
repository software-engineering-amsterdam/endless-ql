package org.uva.sea.gui.renderer;

import org.uva.sea.gui.model.BaseQuestionModel;

@FunctionalInterface
public interface QuestionRenderer {

    void render(BaseQuestionModel questionRow);
}
