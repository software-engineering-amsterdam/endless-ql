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
            this.checkInvalidArithmaticExpression();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkReferenceUndefinedVariable() throws ReferenceUndefinedVariableException {
        boolean found = false;
        for (Variable referencedVariable : formNode.getFormData().getReferencedVariables()){
            if(referencedVariable == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }
    public void checkInvalidArithmaticExpression() throws InvalidAritmaticExpressionException{
        for (QuestionASTNode qs : formNode.getFormData().getAllQuestions()){
            Value qVal = qs.getVariable().getValue();
            if (qVal != null){
                try{
                    qVal.getValue();
                }catch(NumberFormatException nfe){
                    throw new InvalidAritmaticExpressionException("Invalid arithmatic expression found.");
                }
            }
        }
    }

    public void checkDuplicateQuestionDeclaration() {

    }
}
