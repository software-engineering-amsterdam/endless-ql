package loader.QL;

import domain.model.ast.*;
import domain.model.variable.BooleanVariable;
import domain.model.variable.Variable;
import exception.DuplicateQuestionDeclarationException;
import exception.InvalidConditionException;
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
            this.checkInvalidConditionExpressionException();
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
    public void checkInvalidConditionExpressionException() throws InvalidConditionException {
        for (ASTNode an : formNode.getASTNodes()){
            if (an instanceof IfASTNode){
                for (Condition c : ((IfASTNode) an).getConditions()){
                    if (c.getVariable() == null || !(c.getVariable() instanceof BooleanVariable)){
                        throw new InvalidConditionException("Invalid condition found in the conditions of an if statement");
                    }
                }
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
