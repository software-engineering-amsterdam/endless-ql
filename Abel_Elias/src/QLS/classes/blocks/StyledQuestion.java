package QLS.classes.blocks;

import QL.classes.Question;
import QLS.classes.widgets.Widget;

public class StyledQuestion extends Element {

    private String name;
    private Widget widget;
    private Question question;
    private String parentId;

    public StyledQuestion(String name, Widget widget, Question question, String parentId) {
        super();
        this.name = name;
        this.widget = widget;
        this.question = question;
        this.parentId = parentId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Widget getWidget() {
        return this.widget;
    }

    public Question getQuestion() {
        return this.question;
    }

    public String getParentId() {
        return this.parentId;
    }
}