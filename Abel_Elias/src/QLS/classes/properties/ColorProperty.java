package QLS.classes.properties;

import gui.panels.QuestionPanel;

import java.awt.*;

public class ColorProperty extends Property {

    private final Color color;

    public ColorProperty(Color color) {
        this.color = color;
    }

    @Override
    public void applyProperty(QuestionPanel panel) {
        panel.setColor(color);

    }
}