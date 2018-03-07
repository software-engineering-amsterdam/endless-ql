package org.uva.sea.gui.model;

public interface BaseQuestionGUI {

    void accept(QuestionModelVisitor visitor);

    String displayValue();

}
