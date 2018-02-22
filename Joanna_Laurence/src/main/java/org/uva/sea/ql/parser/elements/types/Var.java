package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Var extends ASTNode  {
    private String variableName;

    private Question linkedQuestion = null;

    public Var(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public Question getLinkedQuestion() {
        return linkedQuestion;
    }

    @Override
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public void setLinkedQuestion(Question linkedQuestion) {
        this.linkedQuestion = linkedQuestion;
    }

    public Type getType() {
        if(this.linkedQuestion == null) {
            System.out.println("Variable information should be set before requesting type information");
            return new Type("undefined");
        }

        return this.linkedQuestion.getType();
    }
}
