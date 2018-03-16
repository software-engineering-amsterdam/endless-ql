package domain.model;

import domain.model.variable.Variable;

public class QuestionASTNode extends ASTNode implements Comparable {
    private String text;
    private Variable variable;

    public QuestionASTNode(String text, Variable variable, boolean visible) {
        this.text = text;
        this.variable = variable;
        this.setVisible(visible);
    }

    public String getText() {
        return text;
    }

    public Variable getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public int compareTo(Object o) {
        QuestionASTNode qan = (QuestionASTNode) o;
        if (qan.getText().equals(this.getText()) && qan.getVariable().getIdentifier().equals(this.getVariable().getIdentifier())){
            return 1;
        }else{
            return 0;
        }
    }
}
