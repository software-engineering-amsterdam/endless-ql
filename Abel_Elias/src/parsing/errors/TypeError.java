package parsing.errors;

public class TypeError extends Error {
    public TypeError(String variableID, String type){
        super("The variable " + variableID + " is not of type " + type);
    }
}
