package parsing.errors;

public class DupVarError extends Error {
    public DupVarError(String variableID){
        super("The variable " + variableID + "was declared more than once");
    }
}
