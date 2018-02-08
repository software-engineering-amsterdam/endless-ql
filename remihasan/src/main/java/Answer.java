public abstract class Answer<T> {
    private final String type;
    Answer(){
        type = getType();
    }
    private T value;
    public void setValue(T value){
        this.value = value;
    }

    public abstract String getType();

    public T getValue() {
        return value;
    }
}