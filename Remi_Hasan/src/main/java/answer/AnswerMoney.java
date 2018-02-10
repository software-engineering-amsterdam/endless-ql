package answer;

public class AnswerMoney extends Answer<Double> {

    private Double value = null;

    public void setValue(String value){
        this.value = Double.parseDouble(value);
    }

    @Override
    public Double getValue() {
        return value;
    }
}