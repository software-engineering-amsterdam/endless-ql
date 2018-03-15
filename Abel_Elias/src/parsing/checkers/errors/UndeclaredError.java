package parsing.checkers.errors;

public class UndeclaredError extends Error {
    public UndeclaredError(String variableID){
        super("The variable " + variableID + " was not declared before being called.");
    }
}
