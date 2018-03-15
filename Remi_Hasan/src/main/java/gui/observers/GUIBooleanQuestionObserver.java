package gui.observers;

import gui.model.GUIBooleanQuestion;
import gui.model.GUIQuestion;
import ql.analysis.SymbolTable;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.expression.variable.ExpressionVariableInteger;

public class GUIBooleanQuestionObserver extends GUIQuestionObserver<GUIBooleanQuestion> {

    public GUIBooleanQuestionObserver(SymbolTable symbolTable, GUIBooleanQuestion guiQuestion) {
        super(symbolTable, guiQuestion);
    }

    @Override
    public void update() {
        symbolTable.setExpression(this.guiQuestion.name, new ExpressionVariableBoolean(null,
                this.guiQuestion.value));
    }
}
