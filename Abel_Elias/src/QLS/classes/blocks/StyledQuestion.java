package QLS.classes.blocks;

import QL.classes.Question;

public class StyledQuestion extends Element {

    private String name;
    private Question question;
    private String parentId;

    public StyledQuestion(String name, Question question, String parentId) {
        super();
        this.name = name;
        this.question = question;
        this.parentId = parentId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Question getQuestion() {
        return this.question;
    }

    public String getParentId() {
        return this.parentId;
    }
}