package Nodes.Term;

import Nodes.*;
import QLExceptions.*;

import java.util.List;

public class Variable extends Term {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    /**
     * This function tries to find the value of the referenced Question.
     * @return the value of the referenced Question.
     * @throws OtherException if the Question isn't answered yet (caught by the Question or Condition)
     * @throws SyntaxException if the Variable references a Question that doesn't exist.
     */
    @Override
    public Term getTerm() throws OtherException, SyntaxException {
        // Get the QLForm node.
        ASTNode node = this.getParent();
        while(node.getParent() != null)
            node = node.getParent();

        // Find the referenced Question.
        // First check the Questions of the QLForm.
        QLForm form = (QLForm) node;
        List<Question> questions = form.getQuestions();
        List<Condition> conditions = form.getConditions();
        Term result = checkQuestions(questions);

        // If the result is found already, don't check the other Questions.
        if(result != null)
            return result;

        // Recursively check the Questions in Conditions, and add any Conditions in Conditions to the list of Conditions that have to be checked.
        for(Condition c : conditions) {
            conditions.addAll(c.getConditions());
            questions = c.getQuestions();
            result = checkQuestions(questions);
            // Return the result as soon as the right Question is found.
            if(result != null)
                return result;
        }

        // Throw exception if no variable is found.
        throw new SyntaxException("A Variable was referenced that doesn't exist", this);
    }

    /**
     * This method checks a list of Questions to see if it's referenced by this Variable.
     * @param questions A list of Questions.
     * @return A result, or null when the referenced Question is not found.
     * @throws OtherException when the Question is found, but not yet set.
     */
    private Term checkQuestions(List<Question> questions) throws OtherException {
        for(Question q : questions) {
            if(q.getName().equals(this.name)) {
                // return the result (Term) of the referenced Question.
                Term result = q.getResult();
                // If the Question isn't visible (hidden behind a Condition) or isn't set yet, throw an OtherException, which is handled by the Question or Condition that is being evaluated.
                if(q.isAvailable() || result == null)
                    throw new OtherException("Variable isn't set yet");
                else
                    return result;
            }
        }
        // Return null when the correct Question isn't found.
        return null;
    }
}
