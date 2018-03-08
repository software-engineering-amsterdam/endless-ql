package domain;

import domain.model.Question;

import java.util.List;

public class FormNode {

    private String formIdentifier;
    private FormData formData;
    private List<Question> questions;

    public FormNode(){
        this.formData = new FormData();
    }

    public void setFormIdentifier(String formIdentifier) {
        this.formIdentifier = formIdentifier;
    }
    public String getFormIdentifier() {
        return formIdentifier;
    }
    public FormData getFormData() {
        return this.formData;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q){
        this.questions.add(q);
    }
}
