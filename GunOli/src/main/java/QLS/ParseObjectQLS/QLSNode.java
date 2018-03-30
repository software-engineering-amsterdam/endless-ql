package QLS.ParseObjectQLS;

import QLS.Analysis.WidgetVisitorInterface;

public abstract class QLSNode {
    private final int line;

    public QLSNode(int line){ this.line = line; }

    public int getLine(){ return line; }
    public abstract <T> T accept(WidgetVisitorInterface<T> visitor);
}
