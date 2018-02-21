package com.chariotit.uva.sc.qdsl.ast.node;

public class ConditionalBlockElement extends FormElement {

    FormElement[] elements;

    String condition;

    public ConditionalBlockElement(FormElement[] elements, String condition) {
        this.elements = elements;
        this.condition = condition;
    }
}
