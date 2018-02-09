package answer;

public class AnswerDate extends Answer<String> {

    private String value = null; // epoch start time

    public void setValue(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}