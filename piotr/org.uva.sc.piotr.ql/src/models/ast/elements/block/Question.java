package models.ast.elements.block;

import grammar.QLParser;
import models.ast.elements.FieldDeclaration;

public class Question implements Block {

    private String label;
    private String variableName;

    private FieldDeclaration fieldDeclaration;

    public Question(QLParser.QuestionContext ctx) {
        this.label = ctx.label.getText();
    }

    @Override
    public void print() {
        System.out.println("Question: {\n" +
                "  label:" + this.label +
                "}");
    }
}
