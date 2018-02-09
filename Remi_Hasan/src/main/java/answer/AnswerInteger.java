package answer;

public class AnswerInteger extends Answer<Integer> {

    private Integer value = null;

    public void setValue(Integer value){
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}