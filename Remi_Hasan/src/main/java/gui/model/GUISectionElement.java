package gui.model;

import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import ql.evaluation.SymbolTable;

public abstract class GUISectionElement {
    public abstract Parent render(SymbolTable symbolTable, InvalidationListener allWidgetsListener);
}
