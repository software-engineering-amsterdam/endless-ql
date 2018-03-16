package qls.model;

import org.antlr.v4.runtime.Token;
import ql.model.expression.ReturnType;
import qls.model.widgets.Widget;

import java.util.List;

public class Default extends QLSNode {

    public final ReturnType type;
    public final List<Widget> widgets;

    public Default(Token token, ReturnType type, List<Widget> widgets) {
        super(token);
        this.type = type;
        this.widgets = widgets;
    }
}
