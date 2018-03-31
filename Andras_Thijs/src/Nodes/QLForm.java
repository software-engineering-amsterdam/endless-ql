package Nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a parsed QL form with the appropriate questions and conditions
 */
public class QLForm extends ASTNode {
    private final String name;
    private final List<Question> questions;
    private final List<Condition> conditions;

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
     * Initiates the parent variable for every child ASTNode.
     */
    public void setParents() {
        for(Question q : questions)
            q.setParents(this);

        for(Condition c : conditions)
            c.setParents(this);
    }

    /**
     * Returns the name of the QL form
     * @return the name of this form
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the QL form's list of questions
     * @return the list of Questions of this Form
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns the QL form's list of conditions
     * @return the list of Conditions of this Form
     */
    public List<Condition> getConditions() {
        return conditions;
    }

    /**
     * Returns all Questions in a QLForm.
     * @return the complete list of Questions.
     */
    public List<Question> getAllQuestions() {
        List<Question> allQuestions = new ArrayList<>(questions);
        List<Condition> allConditions = new ArrayList<>(conditions);

        for(Condition c : allConditions) {
            allQuestions.addAll(c.getQuestions());
            allConditions.addAll(c.getConditions());
        }

        return allQuestions;
    }
}