package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import qlviz.model.style.*;

import java.math.BigDecimal;

public abstract class ControlUIWidget<TWidget extends Control> implements UIWidget {

    protected TWidget node;

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public void setProperty(PropertySetting setting) {
        ParameterValueReader parameterValueReader = new ParameterValueReader();
        setting.getValue().accept(parameterValueReader);
        switch (setting.getPropertyKey()) {
            case "width":
                node.setPrefWidth(parameterValueReader.getNumericValue().doubleValue());
                break;
            case "height":
                node.setPrefWidth(parameterValueReader.getNumericValue().doubleValue());
                break;
        }
    }
}
