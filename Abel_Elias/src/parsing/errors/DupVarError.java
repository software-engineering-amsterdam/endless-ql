package parsing.errors;

public class TypeError extends Error {
    public TypeError(String variableID, String type){
        super("The variable " + variableID + "was not of type " + type);
    }
}
