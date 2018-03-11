package qlviz.gui.renderer.javafx;

import javafx.scene.Node;
import javafx.scene.control.Spinner;
import qlviz.gui.viewModel.question.*;
import qlviz.model.question.*;
import qlviz.model.style.Widget;

import java.math.BigDecimal;


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
                break;
            case Radio:
                break;
            case Slider:
                break;
        }
    }
}
