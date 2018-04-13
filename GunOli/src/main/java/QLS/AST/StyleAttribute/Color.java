package QLS.AST.StyleAttribute;

import GUI.Styles.StyleInterface;
import QLS.Analysis.WidgetVisitorInterface;

public class Color extends Style {
    private String HEX;

    public Color(String color, int line){
        super(line);
        setHEX(color);
    }

    public void setHEX(String HEX) {
        this.HEX = HEX;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return null;
    }

    @Override
    public void apply(StyleInterface widget) {
        widget.setColor(HEX);
    }
}
