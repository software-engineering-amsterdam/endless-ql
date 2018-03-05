package org.uva.sea.ql.gui.model;

public interface BaseQuestionGUI {

    void accept(QuestionModelVisitor visitor);

    String displayValue();

}
