package qlviz.model.style;

public class ColorParameter extends Parameter {

    private final String colorAsHex;

    public ColorParameter(String colorAsHex) {
        this.colorAsHex = colorAsHex;
    }

    public String getColorAsHex() {
        return colorAsHex;
    }
}
