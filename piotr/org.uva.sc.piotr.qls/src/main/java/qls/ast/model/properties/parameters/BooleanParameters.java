package qls.ast.model.properties.parameters;

public class BooleanParameters implements OptionalParameters {
    private String valueTrue;
    private String valueFalse;

    public BooleanParameters(String valueTrue, String valueFalse) {
        this.valueTrue = valueTrue;
        this.valueFalse = valueFalse;
    }

    public String getValueTrue() {
        return valueTrue;
    }

    public String getValueFalse() {
        return valueFalse;
    }
}