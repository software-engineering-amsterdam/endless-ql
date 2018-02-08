public abstract class Answer<T> {

    private T value;

    public void setValue(T value){
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}