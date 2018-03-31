package qls.ast.model.properties;

public abstract class Widget extends TypeProperty {

    public Widget(MetaInformation metaInformation) {
        super(metaInformation);
    }

    static public class BooleanParameters {
        String valueTrue;
        String valueFalse;

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

    static public class IntegerParameters {
        Integer min;
        Integer max;
        Integer step;

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

}
