package QL.ParseObjectsQL;

import java.util.ArrayList;

public class Block {
    private ArrayList<Question> questions;

    public Block(ArrayList<Question> qs){
        setQuestions(qs);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}