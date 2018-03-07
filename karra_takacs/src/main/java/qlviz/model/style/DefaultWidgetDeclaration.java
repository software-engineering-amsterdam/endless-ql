package qlviz.model.style;

import java.util.List;

public class DefaultWidgetDeclaration {

    private final List<PropertySetting> propertySettings;
    private final Widget widget;

    public DefaultWidgetDeclaration(List<PropertySetting> propertySettings, Widget widget) {
        this.propertySettings = propertySettings;
        this.widget = widget;
    }

    public List<PropertySetting> getPropertySettings() {
        return propertySettings;
    }

    public Widget getWidget() {
        return widget;
    }
}
