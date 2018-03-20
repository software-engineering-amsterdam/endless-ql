package domain.model.stylesheet;

import java.util.Map;

public class UIElement {

    private String Identifier;
    private Map<String, String> options;

    public UIElement(String identifier, Map<String, String> options) {
        Identifier = identifier;
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

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
