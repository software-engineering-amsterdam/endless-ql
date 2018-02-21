package com.chariotit.uva.sc.qdsl.ast.node;

public class Question extends FormElement {

    String identifier;

    String text;

    String type;

    public Question(String identifier, String text, String type) {
        this.identifier = identifier;
        this.text = text;
        this.type = type;
    }
}
