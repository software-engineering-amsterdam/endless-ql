package qlviz.model.question;

public interface QuestionVisitor {
    void visit(BooleanQuestion booleanQuestion);
    void visit(DateQuestion dateQuestion);
    void visit(DecimalQuestion decimalQuestion);
    void visit(IntegerQuestion integerQuestion);
    void visit(MoneyQuestion moneyQuestion);
    void visit(StringQuestion stringQuestion);
}
