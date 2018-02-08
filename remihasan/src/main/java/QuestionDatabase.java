import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class QuestionDatabase {

    /*private static QuestionDatabase instance;
    private final ArrayList<Question> questions;
//    private final ArrayList<Condition> conditions;
    private QuestionDatabase(){
        questions = new ArrayList<Question>();
//        conditions = new ArrayList<Condition>();
    }

    public static QuestionDatabase getInstance(){
        if(instance == null){
            instance = new QuestionDatabase();
        }
        return instance;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
//        question.addConditions(conditions);
    }

    public void addQuestionString(String questionString) {
        if(questions.size() > 0){
            Question lastQuestion = questions.get(questions.size() - 1);
            lastQuestion.setText(questionString);
        }
    }

//    public void addCondition(Condition condition) {
//        this.conditions.add(condition);
//    }

//    public void removeCondition() {
//        if(this.conditions.size() > 0){
//            this.conditions.remove(this.conditions.size() - 1);
//        }
//    }

    public void addQuestionType(String questionType) {
        if(questions.size() > 0){
            Question lastQuestion = questions.get(questions.size() - 1);
            // TODO how? is this correct?
            try{
                lastQuestion.setAnswer(AnswerFactory.createAnswer(questionType));
            }  catch (UnknownQuestionTypeException e) {
                e.printStackTrace();
            }
        }
    }

    public void addQuestionName(String questionName) {
        if(questions.size() > 0){
            Question lastQuestion = questions.get(questions.size() - 1);
            lastQuestion.setName(questionName);
        }
    }

    *//**
     * Returns a list of questions in a human readable formatted string
     * @return The formatted string
     *//*
    @Override
    public String toString(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(questions);

        return jsonString;
    }*/
}