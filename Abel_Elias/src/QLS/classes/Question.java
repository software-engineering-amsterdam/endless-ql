package QLS.classes;

import QLS.classes.widgets.Widget;

public class Question {

    private String name;
    private Widget widget;

    public Question(String name, Widget widget) {
        this.name = name;
        this.widget = widget;
    }

    public String getName() {
        return this.name;
    }

    public Widget getWidget() {
        return this.widget;
    }
}