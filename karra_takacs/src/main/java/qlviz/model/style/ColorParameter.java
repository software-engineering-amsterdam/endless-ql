package qlviz.model.style;

import javafx.scene.paint.Color;

public class ColorParameter implements Parameter {

    private final Color color;

    public ColorParameter(String colorAsHex) {
        this.color = Color.web(colorAsHex);
    }

    @Override
    public void accept(ParameterVisitor voidParameterVisitor) {
        voidParameterVisitor.visit(this);
    }
}
