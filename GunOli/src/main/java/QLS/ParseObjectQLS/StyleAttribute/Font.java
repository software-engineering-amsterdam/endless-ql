package QLS.ParseObjectQLS.StyleAttribute;

import GUI.Styles.StyleInterface;
import QLS.Analysis.WidgetVisitorInterface;
import QLS.ParseObjectQLS.Widgets.Widget;

public class Font extends Style {
    private String font;

    public Font(String font, int line){
        super(line);
        this.font = font;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return null;
    }

    @Override
    public void apply(StyleInterface widget) {
        widget.setFont(font);
    }
}
