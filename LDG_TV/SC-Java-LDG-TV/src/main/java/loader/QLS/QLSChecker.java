package loader.QLS;

import domain.model.ast.FormNode;
import domain.model.ast.QuestionASTNode;
import domain.model.variable.Variable;
import exception.NotAllQuestionInPlaceException;
import exception.ReferenceUndefinedVariableException;

import java.util.ArrayList;
import java.util.List;

public class QLSChecker {
    private FormNode formNode;

    public QLSChecker(FormNode formNode) {
        this.formNode = formNode;
    }
    /**
     * Execute the checks for QLS.
     */
    public void doChecks() {
        try {
            this.checkReferenceUndefinedVariable();
            this.checkNotAllQuestionsArePlace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check for referenes of QuestionASTNodes which are not defined in the QL form.
     * @throws ReferenceUndefinedVariableException
     */
    public void checkReferenceUndefinedVariable() throws ReferenceUndefinedVariableException {
        for (Variable v : formNode.getStylesheet().getAllVariables()){
            if (v == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }

    /**
     * Checks if all the questions defined in QL are also defined in the QLS.
     * @throws NotAllQuestionInPlaceException
     */
    public void checkNotAllQuestionsArePlace() throws NotAllQuestionInPlaceException {
        List<QuestionASTNode> temp = new ArrayList<>();
        for (Variable v : formNode.getStylesheet().getAllVariables()){
            temp.add(formNode.getQuestionByVariableIdentifier(v.getIdentifier()));
        }
        if(formNode.getAllQuestionASTNodes().retainAll(temp)){
            throw new NotAllQuestionInPlaceException("Not all questions defined in QL are defined in QLS.");
        };
    }
}
