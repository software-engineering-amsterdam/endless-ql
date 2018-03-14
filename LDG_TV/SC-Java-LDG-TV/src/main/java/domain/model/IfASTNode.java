package domain.model;

import domain.model.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class IfASTNode extends ASTNode {

    private List<Condition> conditions;
    private List<QuestionASTNode> questionNodes;

    public IfASTNode(boolean visible) {
        this.questionNodes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.setVisible(visible);
    }

    public List<QuestionASTNode> getQuestionNodes() {
        return questionNodes;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void addQuestion(QuestionASTNode q){
        this.questionNodes.add(q);
    }
    public void addCondition(Condition c){this.conditions.add(c);}

    public boolean checkConditions() {
        boolean temp = true;
        for (int i = 0; i < this.conditions.size(); i++) {
            Condition condition = this.conditions.get(i);
            String operator = condition.getOperator();
            if (operator != null){
                switch (operator) {
                    case "||":
                        temp = temp || (Boolean) condition.getVariable().getValue().getValue();
                    case "&&":
                        temp = temp && (Boolean) condition.getVariable().getValue().getValue();
                }
            }else{
                return (Boolean) condition.getVariable().getValue().getValue();
            }
            return temp;
        }
        return temp;
    }

    private void visitBooleanOperation(Variable lh, String operator, Variable rh){

    }
}
