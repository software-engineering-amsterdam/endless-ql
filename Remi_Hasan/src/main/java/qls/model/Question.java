package qls.model;

import qls.model.widgets.Widget;

public class Question {

    public final String name;
    public final Widget widget;

    public Question(String name, Widget widget) {
        this.name = name;
        this.widget = widget;
    }

}
