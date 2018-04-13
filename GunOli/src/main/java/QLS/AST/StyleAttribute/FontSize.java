package QLS.AST.StyleAttribute;

import GUI.Styles.StyleInterface;
import QLS.Analysis.WidgetVisitorInterface;

public class FontSize extends Style {
    private int fontSize;

    public FontSize(int line, int fontSize) {
        super(line);
        this.fontSize = fontSize;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void apply(StyleInterface widget) {
        widget.setFontSize(fontSize);

    }
}
