package QLS.classes.widgets.properties;

import QLS.classes.widgets.WidgetProperty;

public class ColorWidgetProperty extends WidgetProperty {

    private String color;

    public ColorWidgetProperty(String color) {
        super();
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
