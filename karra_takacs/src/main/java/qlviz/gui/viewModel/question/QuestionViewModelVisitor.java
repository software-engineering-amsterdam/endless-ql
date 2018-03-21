package qlviz.gui.viewModel.question;

public interface QuestionViewModelVisitor {
    void visit(BooleanQuestionViewModel booleanQuestion);
    void visit(DateQuestionViewModel dateQuestion);
    void visit(DecimalQuestionViewModel decimalQuestion);
    void visit(IntegerQuestionViewModel integerQuestion);
    void visit(MoneyQuestionViewModel moneyQuestion);
    void visit(StringQuestionViewModel stringQuestion);

}

