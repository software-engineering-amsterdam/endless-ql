public class BooleanAnswer extends Answer<Boolean> {

    BooleanAnswer(){
        super.setAnswer(false);
    }

    public void setAnswer(Boolean answer){
        super.setAnswer(answer);
    }

    @Override
    public String getAnswerTypeString() {
        return "boolean";
    }


}
