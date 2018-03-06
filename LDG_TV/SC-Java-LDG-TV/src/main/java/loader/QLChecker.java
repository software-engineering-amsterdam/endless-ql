package loader;

import domain.FormNode;
import domain.model.variable.Variable;
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
        boolean found = false;
        for (Variable referencedVariable : formNode.getFormData().getReferencedVariables()){
            if(referencedVariable == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found");
            }
        }
    }

    public void checkDuplicateQuestionDeclaration() {

    }
}
