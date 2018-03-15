package parsing.errors;

public class undeclaredVariableError extends Error {
    public undeclaredVariableError(String variableID){
        super("The variable " + variableID + " was not defined before being called.");
    }
}
