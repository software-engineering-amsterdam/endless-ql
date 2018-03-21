package domain.model.ast;

import domain.model.variable.Variable;

public class QuestionNode extends ASTNode implements Comparable {
    private String text;
    private Variable variable;

    public QuestionNode(String text, Variable variable, boolean disabled) {
        this.text = text;
        this.variable = variable;
        this.setDisabled(disabled);
    }

    /**
     * Compares an QuestionNode object against another QuestionNode whether the text and variable identifier are the same.
     * @param o the compared QuestionNode
     * @return returns 1 if object is the same as param object. If not the same returns 0.
     */
    @Override
    public int compareTo(Object o) {
        QuestionNode qan = (QuestionNode) o;
        if (qan.getText().equals(this.getText()) && qan.getVariable().getIdentifier().equals(this.getVariable().getIdentifier())){
            return 1;
        }else{
            return 0;
        }
    }

    public String getText() {
        return text;
    }

    public Variable getVariable() {
        return variable;
    }

}
