package QLS.AST.StyleAttribute;

import QLS.AST.QLSNode;

public abstract class Style extends QLSNode {

    public Style(int line) {
        super(line);
    }

    public abstract void apply(StyleInterface widget);
}
