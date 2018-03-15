package loader;

import domain.FormNode;
import domain.model.QuestionASTNode;
import domain.model.variable.Variable;
import exception.DuplicateQuestionDeclarationException;
import exception.ReferenceUndefinedVariableException;

import java.util.List;

public class QLChecker {
    private FormNode formNode;

    public QLChecker(FormNode formNode){
        this.formNode = formNode;
    }
    public void doChecks(){
        try {
            this.checkReferenceUndefinedVariable();
            this.checkDuplicateQuestionDeclaration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkReferenceUndefinedVariable() throws ReferenceUndefinedVariableException {
        boolean found = false;
        for (Variable referencedVariable : formNode.getReferencedVariables()){
            if(referencedVariable == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }

    public void checkDuplicateQuestionDeclaration() throws DuplicateQuestionDeclarationException{
        for (QuestionASTNode qan : formNode.getAllQuestionASTNodes()){
            if (foundQuestionMoreThanOnce(qan)){
                throw new DuplicateQuestionDeclarationException("Duplicate question declaration found for question with label: " + qan.getText());
            }
        }
    }
    private boolean foundQuestionMoreThanOnce(QuestionASTNode qan){
        List<QuestionASTNode> temp = formNode.getAllQuestionASTNodes();
        temp.remove(qan);
        for (QuestionASTNode _qan : temp){
            if (_qan.compareTo(qan) == 1){
                return true;
            }
        }
        return false;
    }
}
