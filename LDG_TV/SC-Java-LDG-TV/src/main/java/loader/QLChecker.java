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
        for (String referencedVariable : formNode.getFormData().getReferencedVariables()){
            if (!formNode.getFormData().getPlainQuestionStructures().contains(referencedVariable)){
//                throw new ReferenceUndefinedVariableException("Reference to undefined variable found.");
            }
        }
    }

    public void checkDuplicateQuestionDeclaration() {

    }
}
