package loader;

import domain.FormNode;
import domain.model.QuestionASTNode;
import domain.model.value.Value;
import domain.model.variable.Variable;
import exception.InvalidAritmaticExpressionException;
import exception.ReferenceUndefinedVariableException;

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
            System.out.println(referencedVariable);
            if(referencedVariable == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }

    public void checkDuplicateQuestionDeclaration() {
        for (QuestionASTNode qan : formNode.getAllQuestionASTNodes()){
            //check qan.getText()
            //check qan.getVariable()
            //check
            if (foundQuestionMoreThanOnce(qan)){
                System.out.println("dubble declaration found for " + qan.getText());
            }
        }
    }
    private boolean foundQuestionMoreThanOnce(QuestionASTNode qan){
        int count = 0;
        for (QuestionASTNode _qan : formNode.getAllQuestionASTNodes()){
            if
            (
                qan.getText().equals(_qan.getText()) &&
                qan.getVariable().getIdentifier().equals(_qan.getVariable().getIdentifier())
            ){
                count++;
            }
            if (count == 2){
                return true;
            }
        }
        count = 0;
        return false;
    }
}
