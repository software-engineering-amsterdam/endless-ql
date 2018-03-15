package gui.observers;

import gui.model.GUIDateQuestion;
import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableDate;

public class GUIDateQuestionObserver extends GUIQuestionObserver<GUIDateQuestion> {

    public GUIDateQuestionObserver(SymbolTable symbolTable, GUIDateQuestion guiQuestion) {
        super(symbolTable, guiQuestion);
    }

    @Override
    public void update() {
        symbolTable.setExpression(this.guiQuestion.name, new ExpressionVariableDate(null, this.guiQuestion.value));
    }
}
