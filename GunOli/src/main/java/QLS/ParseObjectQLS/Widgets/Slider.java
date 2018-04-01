package QLS.ParseObjectQLS.Widgets;

import QLS.Analysis.WidgetVisitorInterface;

public class Slider extends Widget {

    Slider(int line) {
        super(WidgetType.Slider, line);
    }



    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
