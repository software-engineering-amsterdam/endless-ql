package QLS.ParseObjectQLS.StyleAttribute;

import GUI.Styles.StyleInterface;
import QLS.Analysis.WidgetVisitorInterface;
import QLS.ParseObjectQLS.Widgets.Widget;

public class Width extends Style{
    private int width;

    public Width(int width, int line) {
        super(line);
        this.width = width;

    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void apply(StyleInterface widget) {
        widget.setWidth(width);
    }
}
