package answer;

public class AnswerString extends Answer<String> {

    private String value = null;

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}