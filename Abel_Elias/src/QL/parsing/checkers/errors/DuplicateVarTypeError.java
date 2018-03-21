package QL.parsing.checkers.errors;

public class DuplicateVarTypeError extends Error {
    public DuplicateVarTypeError(String variableID){
        super("The variable " + variableID + " was declared more than once with different types");
    }
}
