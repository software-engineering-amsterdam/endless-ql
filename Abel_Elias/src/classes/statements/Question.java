package classes.statements;

import classes.CodeBlock;
import classes.expressions.Expression;
import classes.types.ExpressionType;

public class Question<T> extends Statement {
    private String questionText;
    private String identifier;
    private T type;

    public Question(CodeBlock code, String questionText, T type, String identifier){
        super(code);
        this.questionText = questionText;
        this.type = type;
        this.identifier = identifier;
    }

    public String getText(){
        return questionText;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public T getType(){
        return type;
    }

    public ExpressionType getTypeName() {
        String typeText = type.getClass().getSimpleName();
        typeText = typeText.substring(0,1).toUpperCase() + typeText.substring(1);
        return ExpressionType.valueOf(typeText);
    }

    @Override
    public String toString() {
        return type.getClass().getName() + ": " + questionText;
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visitQuestion(this);
    }

    @Override
    public void accept(StatementVisitor visitor, Expression expression) {
        visitor.visitQuestionWithExpr(this, expression);
    }
}
