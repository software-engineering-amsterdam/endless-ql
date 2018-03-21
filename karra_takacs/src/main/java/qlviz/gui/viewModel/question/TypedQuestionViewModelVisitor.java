package qlviz.gui.viewModel.question;

public interface TypedQuestionViewModelVisitor<T> {
    T visit(BooleanQuestionViewModel booleanQuestion);
    T visit(DateQuestionViewModel dateQuestion);
    T visit(DecimalQuestionViewModel decimalQuestion);
    T visit(IntegerQuestionViewModel integerQuestion);
    T visit(MoneyQuestionViewModel moneyQuestion);
    T visit(StringQuestionViewModel stringQuestion);

}
