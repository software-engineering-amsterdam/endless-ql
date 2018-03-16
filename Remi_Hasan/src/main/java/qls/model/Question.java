package qls.model;

import org.antlr.v4.runtime.Token;
import qls.model.widgets.Widget;

public class Question extends QLSNode {

    public final String name;
    public final Widget widget;

    public Question(Token token, String name, Widget widget) {
        super(token);
        this.name = name;
        this.widget = widget;
    }

}
