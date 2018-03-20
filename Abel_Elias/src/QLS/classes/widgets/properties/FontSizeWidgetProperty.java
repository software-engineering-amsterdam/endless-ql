package QLS.classes.widgets.properties;

import QLS.classes.widgets.Widget;
import QLS.classes.widgets.WidgetProperty;

public class FontSizeWidgetProperty extends WidgetProperty {

    private String font;

    public FontSizeWidgetProperty(String font) {
        super();
        this.font = font;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }
}

