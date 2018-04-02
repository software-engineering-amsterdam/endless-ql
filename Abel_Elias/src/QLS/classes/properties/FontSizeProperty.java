package QLS.classes.properties;

import gui.panels.QuestionPanel;

public class FontSizeProperty extends Property {

    private final Integer value;

    public FontSizeProperty(int value) {
        this.value = value;
    }

    @Override
    public void applyProperty(QuestionPanel panel) {
        panel.setFontSize(value);
    }
}
