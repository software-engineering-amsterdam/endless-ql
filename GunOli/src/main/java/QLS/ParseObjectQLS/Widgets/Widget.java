package QLS.ParseObjectQLS.Widgets;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.ParseObjectQLS.QLSNode;

public abstract class Widget extends QLSNode {
    private final WidgetType type;

    Widget(WidgetType type, int line){
        super(line);
        this.type = type;
    }

    public WidgetType getType() {
        return type;
    }

    @Override
    public String toString(){
        return this.type.toString();
    }
}
