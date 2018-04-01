package loader.QLS;

import domain.model.ast.FormNode;
import domain.model.ast.QuestionNode;
import domain.model.stylesheet.Stylesheet;
import domain.model.variable.Variable;
import exception.NotAllQuestionInPlaceException;
import exception.ReferenceUndefinedVariableException;

import java.util.ArrayList;
import java.util.List;

public class QLSChecker {

    /**
     * Verify stylesheet
     *
     * @param stylesheet stylesheet to be checked
     */
    public boolean verifyStylesheetStructure(Stylesheet stylesheet, FormNode formNode) {
        try {
            this.checkReferenceUndefinedVariable(stylesheet);
            this.checkNotAllQuestionsArePlace(stylesheet, formNode);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Check for referenes of QuestionASTNodes which are not defined in the QL form.
     *
     * @throws ReferenceUndefinedVariableException
     */
    public void checkReferenceUndefinedVariable(Stylesheet stylesheet) throws ReferenceUndefinedVariableException {
        for (Variable v : stylesheet.getAllVariables()) {
            if (v == null) {
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }

    /**
     * Checks if all the questions defined in QL are also defined in the QLS.
     *
     * @throws NotAllQuestionInPlaceException
     */
    public void checkNotAllQuestionsArePlace(Stylesheet stylesheet, FormNode formNode) throws NotAllQuestionInPlaceException {
        List<QuestionNode> temp = new ArrayList<>();
        for (Variable v : stylesheet.getAllVariables()) {
            temp.add(formNode.getQuestionByVariableIdentifier(v.getIdentifier()));
        }
        if (formNode.getAllQuestionASTNodes().retainAll(temp)) {
            throw new NotAllQuestionInPlaceException("Not all questions defined in QL are defined in QLS.");
        }
        ;
    }
}
