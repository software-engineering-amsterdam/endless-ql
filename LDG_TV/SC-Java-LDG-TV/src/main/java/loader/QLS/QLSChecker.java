package loader.QLS;

import domain.FormNode;
import domain.model.QuestionASTNode;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
import domain.model.stylesheet.Stylesheet;
import domain.model.variable.Variable;
import exception.DuplicateQuestionDeclarationException;
import exception.NotAllQuestionInPlaceException;
import exception.ReferenceUndefinedVariableException;

import javax.swing.text.Style;
import java.util.Collections;
import java.util.List;

public class QLSChecker {
    private FormNode formNode;

    public QLSChecker(FormNode formNode) {
        this.formNode = formNode;
    }

    public void doChecks() {
        try {
            this.checkReferenceUndefinedVariable();
            this.checkNotAllQuestionsArePlace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkReferenceUndefinedVariable() throws ReferenceUndefinedVariableException {
        for (QuestionASTNode q : formNode.getStylesheet().getAllQuestions()){
            if (q == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }
    public void checkNotAllQuestionsArePlace() throws NotAllQuestionInPlaceException {
        if(formNode.getAllQuestionASTNodes().retainAll(formNode.getStylesheet().getAllQuestions())){
            throw new NotAllQuestionInPlaceException("Not all questions defined in QL are defined in QLS.");
        };
    }
}
