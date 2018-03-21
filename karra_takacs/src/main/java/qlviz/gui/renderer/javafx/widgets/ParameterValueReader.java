package qlviz.gui.renderer.javafx.widgets;

import javafx.scene.paint.Color;
import qlviz.model.style.ColorParameter;
import qlviz.model.style.NumericParameter;
import qlviz.model.style.ParameterVisitor;
import qlviz.model.style.StringParameter;

import java.math.BigDecimal;

public class ParameterValueReader implements ParameterVisitor {

    private String stringValue;
    private BigDecimal numericValue;
    private Color colorValue;

    public String getStringValue() {
        return stringValue;
    }

    public BigDecimal getNumericValue() {
        return numericValue;
    }

    public Color getColorValue() {
        return colorValue;
    }

    @Override
    public void visit(StringParameter stringParameter) {
        this.stringValue = stringParameter.getValue();
        this.numericValue = BigDecimal.ZERO;
        this.colorValue = Color.gray(0);
    }

    @Override
    public void visit(NumericParameter numericParameter) {
        this.numericValue = numericParameter.getValue();
        this.stringValue = "";
        this.colorValue = Color.gray(0);
    }

    @Override
    public void visit(ColorParameter colorParameter) {
        this.colorValue = colorParameter.getValue();
        this.numericValue = BigDecimal.ZERO;
        this.stringValue = "";
    }
}
