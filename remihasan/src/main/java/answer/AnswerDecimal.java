package answer;

public class AnswerDecimal extends Answer<Double> {

    private Double value = null;

    public void setValue(Double value){
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}