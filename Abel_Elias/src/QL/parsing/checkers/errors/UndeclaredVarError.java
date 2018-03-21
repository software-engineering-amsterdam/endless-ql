package QL.parsing.checkers.errors;

public class UndeclaredVarError extends Error {
    public UndeclaredVarError(String variableID){
        super("The variable " + variableID + " was not declared before being called.");
    }
}
