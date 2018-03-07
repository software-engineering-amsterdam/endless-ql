package qlviz.model.style;

public class StringParameter extends Parameter {

    private final String value;

    public StringParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
