package loader.QLS;

import domain.model.ast.FormNode;
import domain.model.ast.QuestionASTNode;
import exception.NotAllQuestionInPlaceException;
import exception.ReferenceUndefinedVariableException;

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
        for (QuestionASTNode q : formNode.getStylesheet().getAllQuestionASTNodes()){
            if (q == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }

    /**
     * Checks if all the questions defined in QL are also defined in the QLS.
     * @throws NotAllQuestionInPlaceException
     */
    public void checkNotAllQuestionsArePlace() throws NotAllQuestionInPlaceException {
        if(formNode.getAllQuestionASTNodes().retainAll(formNode.getStylesheet().getAllQuestionASTNodes())){
            throw new NotAllQuestionInPlaceException("Not all questions defined in QL are defined in QLS.");
        };
    }
}
