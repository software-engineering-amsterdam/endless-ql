package QLS.classes.properties;

import gui.panels.QuestionPanel;

public class WidthProperty extends Property {

    private final int value;

    public WidthProperty(int value) {
        this.value = value;
    }

    @Override
    public void applyProperty(QuestionPanel panel) {
        panel.setWidth(value);
    }
}
