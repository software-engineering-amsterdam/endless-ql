import org.antlr.v4.runtime.tree.TerminalNode;

public class AnswerFactory {
    public static Answer createAnswer(String questionType) {
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
                return new BooleanAnswer();
        }
    }
}
