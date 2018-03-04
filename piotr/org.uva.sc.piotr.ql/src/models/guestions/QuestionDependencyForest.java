package models.guestions;

import models.guestions.graph.elements.QuestionVertex;

import java.util.HashMap;

public class QuestionDependencyForest {

    private HashMap<String, QuestionVertex> questions = new HashMap<>();

    public HashMap<String, QuestionVertex> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<String, QuestionVertex> questions) {
        this.questions = questions;
    }

    public QuestionVertex addQuestion(QuestionVertex question) {
        return this.questions.put(question.getName(), question);
    }

    public QuestionVertex getQuestionVertexByName(String name) {
        return this.questions.get(name);
    }
}
