package ParseObjects;

import java.util.ArrayList;

public class Block {
    private ArrayList<Question> questions;
    private ArrayList<Condition> conditions;

    public Block(ArrayList<Question> qs, ArrayList<Condition> cs){
        setQuestions(qs);
        setConditions(cs);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(ArrayList<Condition> conditions) {
        this.conditions = conditions;
    }
}