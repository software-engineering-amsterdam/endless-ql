package QLS.classes.blocks;

import QLS.classes.widgets.Widget;

public class Question extends Element {

    private String name;
    private Widget widget;

    public Question(String name, Widget widget) {
        super();
        this.name = name;
        this.widget = widget;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Widget getWidget() {
        return this.widget;
    }
}