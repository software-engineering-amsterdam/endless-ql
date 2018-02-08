public class BooleanAnswer extends Answer<Boolean> {

    BooleanAnswer(){
        super.setValue(false);
    }

    public void setValue(Boolean value){
        super.setValue(value);
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
