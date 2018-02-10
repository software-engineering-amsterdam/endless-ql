package answer;

public class AnswerBoolean extends Answer<Boolean> {

    private Boolean value = null;

    // TODO if parse fails do something special
    public void setValue(String value){
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    public String toString(){
        return String.valueOf(value);
    }
}
