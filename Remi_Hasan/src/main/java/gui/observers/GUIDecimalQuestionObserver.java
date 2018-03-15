package gui.observers;

import gui.model.GUIDecimalQuestion;
import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableDecimal;
import ql.model.expression.variable.ExpressionVariableInteger;

public class GUIDecimalQuestionObserver extends GUIQuestionObserver<GUIDecimalQuestion> {

    public GUIDecimalQuestionObserver(SymbolTable symbolTable, GUIDecimalQuestion guiQuestion) {
        super(symbolTable, guiQuestion);
    }

    @Override
    public void update() {
        symbolTable.setExpression(this.guiQuestion.name, new ExpressionVariableDecimal(null,
                Double.parseDouble(this.guiQuestion.value)));
    }
}
