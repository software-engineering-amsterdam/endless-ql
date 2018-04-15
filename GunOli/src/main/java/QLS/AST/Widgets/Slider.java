package QLS.AST.Widgets;

import QLS.Analysis.WidgetVisitorInterface;

public class Slider extends Widget {

    public Slider(int line) {
        super(WidgetType.Slider, line);
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
