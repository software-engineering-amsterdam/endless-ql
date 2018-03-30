package QLS.parsing.checkers.errors;

public class NonExistingQuestionError extends Error {
    public NonExistingQuestionError(String questionID){
        super("The question " + questionID + " does not exist.");
    }
}
