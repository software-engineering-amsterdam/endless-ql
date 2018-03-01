package org.uva.sea.ql.gui;

import org.uva.sea.ql.gui.model.*;

public interface QuestionGuiVisitor {

    void visit(BooleanQuestionGUI value);

    void visit(DateQuestionGUI value);

    void visit(DecimalQuestionGUI value);

    void visit(ErrorQuestionGUI value);

    void visit(IntQuestionGUI value);

    void visit(MoneyQuestionGUI value);

    void visit(StringQuestionGUI value);
}
