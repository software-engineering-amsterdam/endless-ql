package model.stylesheet;

import model.expression.ReturnType;
import model.stylesheet.widgets.Widget;

import java.util.List;

public class Default {

    public final ReturnType type;
    public final List<Widget> widgets;

    public Default(ReturnType type, List<Widget> widgets) {
        this.type = type;
        this.widgets = widgets;
    }
}
