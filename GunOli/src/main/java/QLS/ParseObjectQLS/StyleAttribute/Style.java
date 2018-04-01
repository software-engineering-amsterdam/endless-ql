package QLS.ParseObjectQLS.StyleAttribute;

import GUI.Styles.StyleInterface;
import QLS.ParseObjectQLS.QLSNode;

public abstract class Style extends QLSNode {

    public Style(int line) {
        super(line);
    }

    public abstract void apply(StyleInterface widget);
}
