package org.uva.qls.ast.Style.StyleProperty;

import org.uva.gui.widgets.QuestionWidget;
import org.uva.qls.ast.Value.ColorValue;

import java.awt.*;

public class ColorProperty extends StyleProperty {

    private Color color;

    public ColorProperty(ColorValue colorValue) {
        color = Color.LIGHT_GRAY;
        try {
            color = Color.decode(colorValue.getValue());
        }
        catch (Exception ex) {

        }
    }

    @Override
    public void apply(QuestionWidget widget) {
        widget.setColor(this.color);
    }
}
