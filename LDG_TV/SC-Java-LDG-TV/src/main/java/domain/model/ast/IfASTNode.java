package domain.model.ast;

import java.util.ArrayList;
import java.util.List;

public class IfASTNode extends ASTNode {

    private List<Condition> conditions;
    private List<QuestionASTNode> questionNodes;
    private List<QuestionASTNode> elseNodess;
    public IfASTNode(boolean visible) {
        this.questionNodes = new ArrayList<>();
        this.conditions = new ArrayList<>();
        this.elseNodess = new ArrayList<>();
        this.setDisabled(visible);
    }

    /**
     * Add a QuestionASTNode to the elseNodes list.
     * @param elseNode QuestionASTNode to add.
     */
    public void addElseQuestion(QuestionASTNode elseNode) {
        this.elseNodess.add(elseNode);
    }

    /**
     * Adds a QuestionASTNode to the questionNodes list.
     * @param q QuestionASTNode to add
     */
    public void addQuestion(QuestionASTNode q){
        this.questionNodes.add(q);
    }

    /**
     * Adds a Condition to the conditions list.
     * @param c Condition to add
     */
    public void addCondition(Condition c){this.conditions.add(c);}

    /**
     * Checks whether the conditions for this IfASTNode are satisfied.
     * @return boolean whether conditions are satisfied.
     */
    public boolean checkConditions() {
        boolean temp = true;
        for (int i = 0; i < this.conditions.size(); i++) {
            Condition condition = this.conditions.get(i);
            String operator = condition.getOperator();
            if (operator != null) {
                switch (operator) {
                    case "||":
                        temp = temp || (Boolean) condition.getVariable().getValue().getValue();
                    case "&&":
                        temp = temp && (Boolean) condition.getVariable().getValue().getValue();
                }
            } else {
                temp = (Boolean) condition.getVariable().getValue().getValue();
            }
        }
        for (QuestionASTNode qan : this.getQuestionNodes()){
            qan.setDisabled(!temp);
        }
        for (QuestionASTNode qan : this.elseNodess){
            qan.setDisabled(temp);
        }
        return temp;
    }
    public List<QuestionASTNode> getQuestionNodes() {
        return questionNodes;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<QuestionASTNode> getElseNodess() {
        return elseNodess;
    }


    public String toString () {

        StringBuilder str = new StringBuilder("if {\n");
        for (QuestionASTNode qn : questionNodes) {
            str.append('\t')
                    .append(qn.toString())
                    .append('\n');
        }

        str.append('}');

        return str.toString();
    }
}