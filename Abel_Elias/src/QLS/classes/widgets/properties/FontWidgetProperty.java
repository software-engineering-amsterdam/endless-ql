package QLS.classes.widgets.properties;

import QLS.classes.widgets.WidgetProperty;

public class FontWidgetProperty extends WidgetProperty {

    private Integer fontSize;

    public FontWidgetProperty(Integer fontSize) {
        super();
        this.fontSize = fontSize;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }
}
