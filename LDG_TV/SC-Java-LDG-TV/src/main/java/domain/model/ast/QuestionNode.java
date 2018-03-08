package domain.model.ast;

public class QuestionNode {
    private String text;
    private VariableNode variable;

    public QuestionNode(String text, VariableNode variable) {
        this.text = text;
        this.variable = variable;
    }

    public String getText() {
        return text;
    }

    public VariableNode getVariable() {
        return variable;
    }

    public void setVariable(VariableNode variable) {
        this.variable = variable;
    }
}
