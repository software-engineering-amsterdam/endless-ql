package models.ast.elements;

import grammar.QLParser;

public class QuestionBlock implements Block {

    private String label;
    private Field field;

    public QuestionBlock(QLParser.QuestionContext ctx) {
        this.label = ctx.label.getText();
    }

    @Override
    public void print() {
        System.out.println("Question: {\n" +
                "  label:" + this.label +
                "}");
    }
}
