package qlviz.model.style;

import javafx.scene.paint.Color;

import java.math.BigDecimal;

public interface Parameter {
    void accept(ParameterVisitor voidParameterVisitor);
}

