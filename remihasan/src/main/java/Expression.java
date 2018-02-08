public abstract class Expression<T> {
    public abstract T evaluate();

    public Class resultType(){
        return evaluate().getClass();
    }
}
