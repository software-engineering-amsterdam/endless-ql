package qlviz.gui.renderer.javafx;

import qlviz.gui.renderer.javafx.widgets.*;
import qlviz.model.style.Widget;


public class JavafxWidgetFactory {

    public UIWidget create(Widget widgetDefinition) {
        switch (widgetDefinition.getType()) {
            case Spinbox:
                return new SpinboxUIWidget();
            case Checkbox:
                return new CheckboxUIWidget();
            case Text:
                return new TextFieldUIWidget();
            case Dropdown:
                return new DropdownUIWidget(widgetDefinition.getParameters());
            case Radio:
                return new RadioUIWidget(widgetDefinition.getParameters());
            case Slider:
                return new SliderUIWidget();
        }
        return null;
    }
}
