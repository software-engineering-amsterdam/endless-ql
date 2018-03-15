package gui.observers;

import gui.model.GUIIntegerQuestion;
import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableInteger;

public class GUIIntegerQuestionObserver extends GUIQuestionObserver<GUIIntegerQuestion> {

    public GUIIntegerQuestionObserver(SymbolTable symbolTable, GUIIntegerQuestion guiQuestion) {
        super(symbolTable, guiQuestion);
    }

    @Override
    public void update() {
        symbolTable.setExpression(this.guiQuestion.name, new ExpressionVariableInteger(null,
                Integer.parseInt(this.guiQuestion.value)));
    }
}
