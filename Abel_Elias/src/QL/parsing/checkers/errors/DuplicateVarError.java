package QL.parsing.checkers.errors;

public class DuplicateVarError extends Error {
    public DuplicateVarError(String variableID){
        super("The variable " + variableID + " was declared with multiple types");
    }
}
