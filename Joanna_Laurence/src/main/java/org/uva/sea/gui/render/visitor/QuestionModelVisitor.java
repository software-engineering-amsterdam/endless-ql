package org.uva.sea.gui.render.visitor;

import org.uva.sea.gui.model.*;

public interface QuestionModelVisitor<T> {

    T visit(BooleanQuestionModel question);

    T visit(DateQuestionModel question);

    T visit(DecimalQuestionModel question);

    T visit(ErrorQuestionModel question);

    T visit(IntQuestionModel question);

    T visit(MoneyQuestionModel question);

    T visit(StringQuestionModel question);
}
