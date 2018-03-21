package QL.parsing.checkers.errors;

public class CyclicError extends Error {
    public CyclicError(String variableID1, String variableID2){
        super("Cyclic dependency found for the variables " + variableID1 + " and " + variableID2);
    }
}
