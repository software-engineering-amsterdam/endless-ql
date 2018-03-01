package loader;

import domain.FormNode;
import exception.ReferenceUndefinedVariableException;

public class QLChecker {
    private FormNode formNode;

    public QLChecker(FormNode formNode){
        this.formNode = formNode;
    }
    public void doChecks(){
        try {
            this.checkReferenceUndefinedVariable();
        } catch (ReferenceUndefinedVariableException e) {
            e.printStackTrace();
        }
    }
    public void checkReferenceUndefinedVariable() throws ReferenceUndefinedVariableException {
        for (String key : formNode.getFormData().getConditionQuestions().keySet()){
            if (!formNode.getFormData().getPlainQuestions().contains(key)){
                throw new ReferenceUndefinedVariableException("Reference to undefined variable found.");
            }
        }
    }

    public void checkDuplicateQuestionDeclaration() {
        
    }
}
