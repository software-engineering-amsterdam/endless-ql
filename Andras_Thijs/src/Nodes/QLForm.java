package Nodes;

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

    public List<Question> getAllQuestions(){
        List<Question> allQuestions = questions;
        for (Condition condition : conditions) {
            List<Question> subQuestions = visitCondition(condition);
            if (subQuestions != null)
                allQuestions.addAll(subQuestions);
        }
        return allQuestions;
    }

    private List<Question> visitCondition(Condition condition){

        List<Question> questions = condition.getQuestions();
        List<Condition> conditions = condition.getConditions();
        for (Condition condition1 : conditions) {
            List<Question> subQuestions = visitCondition(condition1);
            if (subQuestions != null)
                questions.addAll(subQuestions);
        }
        return questions;
    }
}