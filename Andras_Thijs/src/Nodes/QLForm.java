package Nodes;

import java.util.List;

/**
 * Contains a parsed QL form with the appropriate questions and conditions
 */
public class QLForm extends ASTNode {
    private String name;
    private List<Question> questions;
    private List<Condition> conditions;


    /**
     * Creates a QLForm with just a name
     * @param name contains the name of the form
     */
    public QLForm(String name){
        this.name = name;
    }

    /**
     * Creates a QL form with a name and a set of questions
     * @param name contains the name of the form
     * @param questions contains a list of Questions
     */
    public QLForm(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    /**
     * Creates a QL form with a name, a set of questions, and a set of conditions
     * @param name contains the name of the form
     * @param questions contains a list of Questions
     * @param conditions contains a list of Conditions
     */
    public QLForm(String name, List<Question> questions, List<Condition> conditions) {
        this.name = name;
        this.questions = questions;
        this.conditions = conditions;
    }

    /**
     * Returns the name of the QL form
     * @return the name of this form
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the QL form's list of questions
     * @return the list of Questions of this Form
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Returns the QL form's list of conditions
     * @return the list of Conditions of this Form
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * Adds a question to the QL form
     * @param question the Question that needs to be added
     */
    public void addQuestion(Question question){
        this.questions.add(question);
    }
}