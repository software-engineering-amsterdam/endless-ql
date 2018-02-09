package model;

import java.util.ArrayList;

public class Block {

    public final ArrayList<Question> questions;
    public final ArrayList<Condition> conditions;

    public Block(ArrayList<Question> questions, ArrayList<Condition> conditions){
        this.questions = questions;
        this.conditions = conditions;
    }
}
