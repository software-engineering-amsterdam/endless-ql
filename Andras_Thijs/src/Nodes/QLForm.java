package Nodes;

import java.util.Iterator;
import java.util.List;
import QLExceptions.SyntaxException;

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
     * @param nodes contains either a list of Questions or a list of Conditions
     * @throws UnsupportedOperationException when the type is not Questions or Conditions
     */
    public QLForm(String name, List<? extends ASTNode> nodes) throws SyntaxException {
        this.name = name;
        ASTNode first = nodes.get(0);
        if (first instanceof Question) {
            this.questions = (List<Question>) nodes;
        } else if (first instanceof Condition) {
            this.conditions = (List<Condition>) nodes;
        } else {
            throw new SyntaxException("Received a List that doesn't contain Questions or Conditions", this);
        }
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
     * Initiates the parent variable for every child ASTNode.
     */
    public void setParents() {
        if(questions != null)
            for(Question q : questions)
                q.setParents(this);

        if(conditions != null)
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

    /**
     * Adds a question to the QL form
     * @param question the Question that needs to be added
     */
    public void addQuestion(Question question){
        this.questions.add(question);
    }


    public List<Question> getAllQuestions(){
        List<Question> allQuestions = questions;
        if(conditions != null){
            Iterator<Condition> conditionIterator = conditions.iterator();
            while (conditionIterator.hasNext()) {
                List<Question> subQuestions = visitCondition(conditionIterator.next());
                if (subQuestions != null)
                    allQuestions.addAll(subQuestions);
            }
        }
        return allQuestions;
    }

    private List<Question> visitCondition(Condition condition){

        List<Question> questions = condition.getQuestions();
        List<Condition> conditions = condition.getConditions();
        Iterator<Condition> conditionIterator = conditions.iterator();
        while (conditionIterator.hasNext()) {
            List<Question> subQuestions = visitCondition(conditionIterator.next());
            if (subQuestions != null)
                questions.addAll(subQuestions);
        }
        return questions;
    }
}