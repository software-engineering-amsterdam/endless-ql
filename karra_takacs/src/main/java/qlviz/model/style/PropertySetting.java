package qlviz.model.style;

public class PropertySetting {

    private final String propertyKey;
    private final Parameter value;

    public PropertySetting(String propertyKey, Parameter value) {
        this.propertyKey = propertyKey;
        this.value = value;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public Parameter getValue() {
        return value;
    }
}
