package gui.observers;

import gui.model.GUIStringQuestion;
import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableString;

public class GUIStringQuestionObserver extends GUIQuestionObserver<GUIStringQuestion> {

    public GUIStringQuestionObserver(SymbolTable symbolTable, GUIStringQuestion guiQuestion) {
        super(symbolTable, guiQuestion);
    }

    @Override
    public void update() {
        symbolTable.setExpression(this.guiQuestion.name, new ExpressionVariableString(null, this.guiQuestion.value));
    }
}
