package QLS.classes.widgets;

import java.util.ArrayList;
import java.util.List;

public class WidgetProperties {

    List<WidgetProperty> properties = new ArrayList<>();

    public WidgetProperties(List<WidgetProperty> properties) {
        super();
        this.properties = properties;
    }

    public WidgetProperties() {
        super();
    }

    public List<WidgetProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<WidgetProperty> properties) {
        this.properties = properties;
    }
}
