package org.uva.sea.gui.model;

public interface QuestionModelVisitor {

    void visit(BooleanQuestionModel question);

    void visit(DateQuestionModel question);

    void visit(DecimalQuestionModel question);

    void visit(ErrorQuestionModel question);

    void visit(IntQuestionModel question);

    void visit(MoneyQuestionModel question);

    void visit(StringQuestionModel question);
}
