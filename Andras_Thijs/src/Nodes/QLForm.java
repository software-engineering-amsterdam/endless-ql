package Nodes;

import java.util.List;

/**
 * Contains a parsed QL form with the appropriate questions and conditions
 */
public class QLForm {
    private String name;
    private List<Question> questions;
    private List<Condition> conditions;


    /**
     * Creates a QLForm with just a name
     * @param name
     */
    public QLForm(String name){
        this.name = name;
    }

    /**
     * Creates a QL form with a name and a set of questions
     * @param name
     * @param questions
     */
    public QLForm(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    /**
     * Creates a QL form with a name, a set of questions, and a set of conditions
     * @param name
     * @param questions
     * @param conditions
     */
    public QLForm(String name, List<Question> questions, List<Condition> conditions) {
        this.name = name;
        this.questions = questions;
        this.conditions = conditions;
    }

    /**
     * Returns the name of the QL form
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the QL form's list of questions
     * @return
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Adds a question to the QL form
     * @param question
     */
    public void addQuestion(Question question){
        this.questions.add(question);
    }

    /**
     * Returns the QL form's list of conditions
     * @return
     */
    public List<Condition> getConditions() {
        return conditions;
    }
}