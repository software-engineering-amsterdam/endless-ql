package domain.model.stylesheet.ui;

import java.util.HashMap;
import java.util.Map;

public class DefaultElement extends UIElement {
    private String Identifier;
    private Map<String, String> options;

    public DefaultElement(String identifier, Map<String, String> options) {
        Identifier = identifier;
        this.options = new HashMap<>();
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
