package answer;

public class AnswerMoney extends Answer<Double> {

    private Double value = null;

    public void setValue(Double value){
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }
}