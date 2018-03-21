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

    /**
     * Execute the checks for QL.
     */
    public void doChecks(){
        try {
            this.checkReferenceUndefinedVariable();
            this.checkDuplicateQuestionDeclaration();
            this.checkInvalidConditionExpressionException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check for references to undefined variables.
     * @throws ReferenceUndefinedVariableException
     */
    public void checkReferenceUndefinedVariable() throws ReferenceUndefinedVariableException {
        boolean found = false;
        for (Variable referencedVariable : formNode.getReferencedVariables()){
            if(referencedVariable == null){
                throw new ReferenceUndefinedVariableException("Reference undefined variable found.");
            }
        }
    }

    /**
     * Check for duplicate question declarations(same text and identifier).
     * @throws DuplicateQuestionDeclarationException
     */
    public void checkDuplicateQuestionDeclaration() throws DuplicateQuestionDeclarationException{
        for (QuestionNode qan : formNode.getAllQuestionASTNodes()){
            if (foundQuestionMoreThanOnce(qan)){
                throw new DuplicateQuestionDeclarationException("Duplicate question declaration found for question with label: " + qan.getText());
            }
        }
    }

    /**
     * Check for invalid boolean conditions in ifASTNodes conditions list.
     * @throws InvalidConditionException
     */
    public void checkInvalidConditionExpressionException() throws InvalidConditionException {
        for (ASTNode an : formNode.getASTNodes()){
            if (an instanceof ConditionNode){
                for (Condition c : ((ConditionNode) an).getConditions()){
                    if (c.getVariable() == null || !(c.getVariable() instanceof BooleanVariable)){
                        throw new InvalidConditionException("Invalid condition found in the conditions of an if statement");
                    }
                }
            }
        }
    }

    /**
     * Check whether an QuestionNode is found more than once in the formNode questionASTNodes list.
     * @param qan QuestionNode to find duplicates for.
     * @return true if QuestionNode is found more then once. False if not.
     */
    private boolean foundQuestionMoreThanOnce(QuestionNode qan){
        List<QuestionNode> temp = formNode.getAllQuestionASTNodes();
        temp.remove(qan);
        for (QuestionNode _qan : temp){
            if (_qan.compareTo(qan) == 1){
                return true;
            }
        }
        return false;
    }
}
