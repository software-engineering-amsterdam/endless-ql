package QLS.AST.Statements;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.AST.Widgets.Widget;

public class QLSQuestion extends Statement {

    private String identifier;
    private Widget widget;


    public QLSQuestion(String identifier, Widget widget, int line){
        super(line);
        this.identifier = identifier;
        this.widget = widget;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Widget getWidget() {
        return widget;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
