package answer;

public abstract class Answer<T> {

    public abstract void setValue(String value);

    public abstract T getValue();
}