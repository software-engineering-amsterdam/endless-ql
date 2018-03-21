package qlviz.model.style;

import javafx.scene.paint.Color;

public class ColorParameter implements Parameter {

    private final Color value;

    public ColorParameter(String colorAsHex) {
        this.value = Color.web(colorAsHex);
    }

    @Override
    public void accept(ParameterVisitor voidParameterVisitor) {
        voidParameterVisitor.visit(this);
    }

    public Color getValue() {
        return value;
    }
}
