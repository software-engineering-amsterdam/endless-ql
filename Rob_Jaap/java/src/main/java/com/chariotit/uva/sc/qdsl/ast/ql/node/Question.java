package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class Question extends AstNode {

    private String question;

    public Question(String question, SourceFilePosition filePosition) {
        super(filePosition);
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitQuestion(this);
    }
}
