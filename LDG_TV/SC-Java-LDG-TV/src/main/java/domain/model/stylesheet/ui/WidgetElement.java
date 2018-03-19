package domain.model.stylesheet.ui;

public class WidgetElement extends UIElement{
    private String identifier;

    public WidgetElement(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
