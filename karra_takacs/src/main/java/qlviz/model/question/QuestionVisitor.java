package qlviz.model.question;

public interface QuestionVisitor<T> {
    T visit(BooleanQuestion booleanQuestion);
    T visit(DateQuestion dateQuestion);
    T visit(DecimalQuestion decimalQuestion);
    T visit(IntegerQuestion integerQuestion);
    T visit(MoneyQuestion moneyQuestion);
    T visit(StringQuestion stringQuestion);
}
