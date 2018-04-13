package QLS.AST.Widgets;

import QLS.Analysis.WidgetVisitorInterface;

public class SpinBox extends Widget {


    public SpinBox(int line) {
        super(WidgetType.SpinBox, line);
    }



    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
