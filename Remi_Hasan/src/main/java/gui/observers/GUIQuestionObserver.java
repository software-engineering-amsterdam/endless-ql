package gui.observers;

import gui.model.GUIQuestion;
import ql.analysis.SymbolTable;

public abstract class GUIQuestionObserver<T extends GUIQuestion> {
    T guiQuestion;
    SymbolTable symbolTable;

    public GUIQuestionObserver(SymbolTable symbolTable, T guiQuestion) {
        this.symbolTable = symbolTable;
        this.guiQuestion = guiQuestion;
        this.guiQuestion.attach(this);
    }

    public abstract void update();
}
