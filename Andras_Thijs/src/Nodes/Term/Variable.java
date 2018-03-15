package Nodes.Term;

import Nodes.ASTNode;
import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.*;

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
     * @return the value of the referenced Question.
     * @throws OtherException if the Question isn't answered yet (caught by the Question or Condition)
     * @throws SyntaxException if the Variable references a Question that doesn't exist.
     */
    @Override
    public Term getTerm() throws OtherException, SyntaxException {
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
                    throw new OtherException("Variable isn't set yet");
                } else {
                    return result;
                }
            }
        }

        // Throw exception if no variable is found.
        throw new SyntaxException("A Variable was referenced that doesn't exist", this);
    }

    public float getValue() {
        return this.value.getValue();
    }
}
