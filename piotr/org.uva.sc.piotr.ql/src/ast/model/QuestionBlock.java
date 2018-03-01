package ast.model;

public class QuestionBlock extends Block {
    private String text;
    private Field field;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public void print() {
        System.out.println("Question block: {\n" +
                "  text:" + this.text +
                "}");
    }
}
