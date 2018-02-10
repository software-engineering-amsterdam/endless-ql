package answer;

public class AnswerInteger extends Answer<Integer> {

    private Integer value = null;

    public void setValue(String value){
        this.value = Integer.parseInt(value);
    }

    @Override
    public Integer getValue() {
        return value;
    }
}