package domain.model.stylesheet;

import java.util.Map;

public class UIElement {

    private String Identifier;
    private String type;
    private Map<String, String> options;

    public UIElement(String identifier, String type, Map<String, String> options) {
        this.Identifier = identifier;
        this.type = type;
        this.options = options;
    }

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        Identifier = identifier;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
