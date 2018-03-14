package Nodes.Term;

import Nodes.ASTNode;
import Nodes.QLForm;
import Nodes.Question;

public class Variable extends Term {
    private String name;
    private Term value;

    public Variable(String name) {
        this.name = name;
    }

    public Variable(String name, Term value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }

    /**
     * This function tries to find the value of the referenced Question.
     * If that Question isn't answered yet, it throws an error to be caught by the calling Expression.
     * @return the value of the referenced Question.
     * @throws ... if the Question isn't answered yet (caught by the Expression)
     * @throws ... if the Question doesn't exist.
     */
    public Term getTerm() {
        // Get the QLForm node.
        ASTNode node = this.getParent();
        while(!(node instanceof QLForm))
            node = node.getParent();

        // Find the referenced Question.
        for(Question q : ((QLForm) node).getQuestions()) {
            if(q.getName().equals(this.name)) {
                // return the result (Term) of the referenced Question.
                Term result = q.getResult();
                if(result == null) {
                    throw new UnsupportedOperationException(); // TODO: Change this to a specific error
                } else {
                    return result;
                }
            }
        }

        // Throw exception if no variable is found.
        throw new UnsupportedOperationException(); // TODO: Change this to a hard failure
    }

    public float getValue() {
        return this.value.getValue();
    }
}
