public class ExpressionIdentifier extends Expression<Boolean>{

    private final String identifier;

    ExpressionIdentifier(String identifier){
        this.identifier = identifier;
    }

    @Override
    public Boolean evaluate() {
        // TODO get real value
        return true;
    }

    @Override
    public boolean isEvaluable() {
        // TODO does the identifier exist?
        return true;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
