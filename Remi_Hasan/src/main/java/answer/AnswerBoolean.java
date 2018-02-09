package answer;

public class AnswerBoolean extends Answer<Boolean> {

    private Boolean value = null;

    public void setValue(Boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
