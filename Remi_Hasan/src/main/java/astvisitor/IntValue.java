package astvisitor;

public class IntValue extends Value<Integer>{
    final Number value;
    IntValue(Number value){
        this.value = value;
    }
}
