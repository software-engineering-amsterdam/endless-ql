package model.stylesheet;

import expression.ReturnType;
import model.stylesheet.widgets.Widget;

import java.util.ArrayList;

public class Default {

    public final ReturnType type;
    public final ArrayList<Widget> widgets;

    public Default(ReturnType type, ArrayList<Widget> widgets){
        this.type = type;
        this.widgets = widgets;
    }
}
