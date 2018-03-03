package Nodes;

import java.util.List;

public class QLForm {
    private String name;
    private List<Question> questions;
    private List<Condition> conditions;


    public QLForm(String name){
        this.name = name;
    }

    public QLForm(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public QLForm(String name, List<Question> questions, List<Condition> conditions) {
        this.name = name;
        this.questions = questions;
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }
}