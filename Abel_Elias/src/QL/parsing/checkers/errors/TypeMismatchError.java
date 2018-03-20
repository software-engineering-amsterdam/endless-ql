package QL.parsing.checkers.errors;

public class TypeMismatchError extends Error {
    public TypeMismatchError(String expression, String type){
        super("The expression below is not of type " + type + "\n" + expression);
    }
}
