package QLS.classes.properties;

import QL.classes.values.StringValue;
import gui.panels.QuestionPanel;

public class FontProperty extends Property {

    private final String stringValue;

    public FontProperty(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public void applyProperty(QuestionPanel panel) {
        panel.setFont(stringValue);
    }
}
