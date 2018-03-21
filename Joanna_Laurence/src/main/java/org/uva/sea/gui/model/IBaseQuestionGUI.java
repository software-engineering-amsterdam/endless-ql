package org.uva.sea.gui.model;

import org.uva.sea.gui.render.visitor.IQuestionModelVisitor;

public interface IBaseQuestionGUI {

    <T> T accept(IQuestionModelVisitor<T> visitor);

    String displayValue();
}
