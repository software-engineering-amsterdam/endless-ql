package model;

import java.util.ArrayList;

public class Block {

    private final ArrayList<Question> questions;
    private final ArrayList<Condition> conditions;

    public Block(ArrayList<Question> questions, ArrayList<Condition> conditions){
        this.questions = questions;
        this.conditions = conditions;
    }
}
