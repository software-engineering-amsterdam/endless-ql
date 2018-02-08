public class BooleanAnswer extends Answer<Boolean> {

    private final String type = "boolean";

    BooleanAnswer(){
        super.setValue(false);
    }

    public void setValue(Boolean value){
        super.setValue(value);
    }
}
