package answer;

public class AnswerFactory {
    public static Answer createAnswer(String questionType) {
        switch(questionType){
            case "boolean":
                return new AnswerBoolean();
            case "string":
                return new AnswerString();
            case "integer":
                return new AnswerInteger();
            case "date":
                return new AnswerDate();
            case "decimal":
                return new AnswerDecimal();
            case "money":
                return new AnswerMoney();
            default:
                throw new IllegalArgumentException("Unknown question type " + questionType);
        }
    }
}
