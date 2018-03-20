package QLS.classes.widgets.properties;

import QLS.classes.widgets.WidgetProperty;

public class WidthWidgetProperty extends WidgetProperty {

    private Integer width;


    public WidthWidgetProperty(Integer width) {
        super();
        this.width = width;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
