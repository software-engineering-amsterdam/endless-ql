package org.uva.sea.gui.model;

public interface BaseQuestionGUI {

    <T> T accept(QuestionModelVisitor<T> visitor);

    String displayValue();
}
