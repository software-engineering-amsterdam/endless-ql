package qls.ast.model.properties.parameters;

public class IntegerParameters implements OptionalParameters {
    private Integer min;
    private Integer max;
    private Integer step;

    public IntegerParameters(Integer min, Integer max, Integer step) {
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getStep() {
        return step;
    }
}
