package org.uva.sea.ql.gui.model;

import org.uva.sea.ql.gui.QuestionGuiVisitor;

public interface BaseQuestionGUI {

    void accept(QuestionGuiVisitor visitor);

    String displayValue();

}
