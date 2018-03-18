package QL.parsing.checkers.errors;

public class TypeMismatchError extends Error {
    public TypeMismatchError(String variableID, String type){
        super("The variable " + variableID + " is not of type " + type);
    }
}
