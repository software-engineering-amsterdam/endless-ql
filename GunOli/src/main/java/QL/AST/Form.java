package QL.AST;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Form extends ASTNode {
    private String name;
    private ArrayList<Question> questions;

    public Form(String name, ArrayList<Question> questions, int lineNumber){
        super(lineNumber);
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getQuestions() { return questions; }

    public Question getQuestion(String id){
        for(Question question : questions){
            if(question.getIdentifier().equals(id)){
                return question;
            }
        }
        throw new IllegalArgumentException("Bad assignment: Question with identifier '"+id+"' not found in Form '"+name+"'.");
    }

    @Override
    public String toString(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}