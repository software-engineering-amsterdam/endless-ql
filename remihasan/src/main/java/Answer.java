public abstract class Answer<T> {
    private T answer;
    public void setAnswer(T answer){
        this.answer = answer;
    }

    public abstract String getAnswerTypeString();

    public T getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        if(getAnswer() == null){
            return getAnswerTypeString();
        } else {
            return getAnswerTypeString() + " (value: " + getAnswer() + ")";
        }
    }
}