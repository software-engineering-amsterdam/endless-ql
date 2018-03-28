package gui.model;

import gui.elements.LabelWithWidget;
import javafx.beans.InvalidationListener;
import ql.evaluation.SymbolTable;

public interface IGUIQuestion {
    LabelWithWidget render(SymbolTable symbolTable, InvalidationListener allWidgetsListener);
}
