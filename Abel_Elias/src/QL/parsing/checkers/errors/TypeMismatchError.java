package QL.parsing.checkers.errors;

public class TypeMismatchError extends Error {
    public TypeMismatchError(String expression, String codeBlock, String type){
        super("\nThe expression:\n " + expression + "\nIn code block:\n" + codeBlock + "\nIs not of type: " + type);
    }
}
