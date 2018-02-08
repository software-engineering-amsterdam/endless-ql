public class UnknownQuestionTypeException extends Exception {
    UnknownQuestionTypeException(String questionType){
        super("Question type [" + questionType + "] is unknown");
    }
}
