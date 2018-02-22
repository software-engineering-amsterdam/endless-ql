package org.uva.sea.ql.parser.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.traverse.Visitor;

public class Variable extends ASTNode  {
    private String variableName;

    private Question linkedQuestion = null;

    public Variable(Token token, String variableName) {
        super(token);
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public Question getLinkedQuestion() {
        return linkedQuestion;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setLinkedQuestion(Question linkedQuestion) {
        this.linkedQuestion = linkedQuestion;
    }

    public Type getType() {
        if(this.linkedQuestion == null) {
            System.out.println("Variable information should be set before requesting type information");
            return new Type(NodeType.UNKNOWN);
        }

        return this.linkedQuestion.getType();
    }
}
