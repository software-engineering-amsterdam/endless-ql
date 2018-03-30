package QLS.ParseObjectQLS.Widgets;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.QLSVisitor.WidgetVisitor;

public class CheckBox extends Widget {

    public CheckBox(int line) {
        super( WidgetType.CheckBox, line);
    }


    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
