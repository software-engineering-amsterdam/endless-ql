package ParseObjects;

import java.util.ArrayList;

public class Block {
    private ArrayList<Question> questions;
    private ArrayList<Condition> conditions;

    public Block(ArrayList<Question> qs, ArrayList<Condition> cs){
        this.questions = qs;
        this.conditions = cs;
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    public void addCondition(Condition c){
        conditions.add(c);
    }
}