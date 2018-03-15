package gui.observers;

import gui.model.GUIMoneyQuestion;
import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableMoney;

public class GUIMoneyQuestionObserver extends GUIQuestionObserver<GUIMoneyQuestion> {

    public GUIMoneyQuestionObserver(SymbolTable symbolTable, GUIMoneyQuestion guiQuestion) {
        super(symbolTable, guiQuestion);
    }

    @Override
    public void update() {
        symbolTable.setExpression(this.guiQuestion.name, new ExpressionVariableMoney(null,
                this.guiQuestion.value));
    }
}
