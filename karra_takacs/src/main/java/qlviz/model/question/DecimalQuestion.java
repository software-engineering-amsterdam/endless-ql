package qlviz.model.question;

public class DecimalQuestion extends NumericQuestion {

    public DecimalQuestion(String name, String text, QuestionType type) {
        super(name, text, type);
    }

    @Override
    public <T> T accept(QuestionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidQuestionVisitor visitor) {
        visitor.visit(this);
    }
}
