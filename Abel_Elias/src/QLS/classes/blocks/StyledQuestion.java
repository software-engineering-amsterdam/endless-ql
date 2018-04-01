package QLS.classes.blocks;

import QL.classes.Question;
import gui.widgets.Widget;

public class StyledQuestion extends Element {

    private String name;
    private Question question;
    private String parentId;
    private Widget widget;

    public StyledQuestion(String name, Question question, String parentId, Widget widget) {
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

    public Question getQuestion() {
        return this.question;
    }

    public String getParentId() {
        return this.parentId;
    }

    public Widget getWidget(){
        return this.widget;
    }
}