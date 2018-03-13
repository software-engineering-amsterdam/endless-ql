package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.QuestionModelVisitor;

public interface BaseQuestionGUI {

    <T> T accept(QuestionModelVisitor<T> visitor);

    String displayValue();
}
