package domain.model.ast;

import domain.model.stylesheet.UIElement;
import domain.model.variable.Variable;

public class QuestionASTNode extends ASTNode implements Comparable {
    private String text;
    private Variable variable;
    private UIElement uiElement;

    public QuestionASTNode(String text, Variable variable, boolean visible) {
        this.text = text;
        this.variable = variable;
        this.setDisabled(visible);
    }

    /**
     * Compares an QuestionASTNode object against another QuestionASTNode whether the text and variable identifier are the same.
     * @param o the compared QuestionASTNode
     * @return returns 1 if object is the same as param object. If not the same returns 0.
     */
    @Override
    public int compareTo(Object o) {
        QuestionASTNode qan = (QuestionASTNode) o;
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

    public void setUiElement(UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public UIElement getUiElement() {
        return uiElement;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
