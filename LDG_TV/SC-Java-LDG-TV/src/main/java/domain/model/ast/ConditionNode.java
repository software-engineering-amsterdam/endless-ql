package domain.model.ast;

import org.mvel2.MVEL;

import java.util.ArrayList;
import java.util.List;

public class ConditionNode extends ASTNode {

    private List<Condition> conditions;
    private List<QuestionNode> questionNodes;
    private ElseNode elseNode;

    public ConditionNode(boolean visible) {
        this.questionNodes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.elseNode = new ElseNode();
        this.setDisabled(visible);
    }

    /**
     * Add a QuestionNode to the elseNodes list.
     * @param elseNode QuestionNode to add.
     */
    public void addElseQuestion(QuestionNode elseNode) {
        this.elseNode.getQuestionNodes().add(elseNode);
    }

    /**
     * Adds a QuestionNode to the questionNodes list.
     * @param q QuestionNode to add
     */
    public void addQuestion(QuestionNode q){
        this.questionNodes.add(q);
    }

    /**
     * Adds a Condition to the conditions list.
     * @param c Condition to add
     */
    public void addCondition(Condition c){this.conditions.add(c);}

    /**
     * Checks whether the conditions for this ConditionNode are satisfied.
     * @return boolean whether conditions are satisfied.
     */
    public boolean checkConditions() {
        boolean temp;
        String booleanExpression = "";
        for (Condition condition : this.conditions) {
            String operator = condition.getOperator();
            if (operator != null) {
                booleanExpression += String.valueOf(condition.getVariable().getValueObject().getValue()) + " " + condition.getOperator();
            } else {
                booleanExpression += String.valueOf(condition.getVariable().getValueObject().getValue());
            }
        }
        temp = (Boolean) MVEL.eval(booleanExpression);
        for (QuestionNode qan : this.getQuestionNodes()){
            qan.setDisabled(!temp);
        }
        for (QuestionNode qan : this.elseNode.getQuestionNodes()){
            qan.setDisabled(temp);
        }
        return temp;
    }
    public List<QuestionNode> getQuestionNodes() {
        return questionNodes;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public ElseNode getElseNode() {
        return elseNode;
    }
}