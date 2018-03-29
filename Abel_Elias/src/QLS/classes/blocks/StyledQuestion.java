package QLS.classes.blocks;

import QL.classes.Question;
import QLS.classes.widgets.Widget;

public class StyledQuestion extends Element {

    private String name;
    private Widget widget;
    private Question question;

    public StyledQuestion(String name, Widget widget, Question question) {
        super();
        this.name = name;
        this.widget = widget;
        this.question = question;
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
}