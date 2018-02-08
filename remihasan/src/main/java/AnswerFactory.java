public class AnswerFactory {
    public static Answer createAnswer(String questionType) throws UnknownQuestionTypeException{
        switch(questionType){
            case "BOOLEANTYPE":
                return new BooleanAnswer();
//                break;
//            case "STRINGTYPE":
//                break;
//            case "INTEGERTYPE":
//                break;
//            case "DATETYPE":
//                break;
//            case "DECIMALTYPE":
//                break;
//            case "MONEYTYPE":
//                break;

            default:
                // unknown?
                throw new UnknownQuestionTypeException(questionType);
        }
    }
}
