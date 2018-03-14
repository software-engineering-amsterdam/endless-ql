package qlviz.gui.renderer.layout;

import qlviz.model.style.Question;
import qlviz.model.style.Scope;

import java.util.Stack;

public class QuestionLocation {

    private final Stack<Scope> scopes = new Stack<>();
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Stack<Scope> getScopes() {return scopes;}
}
