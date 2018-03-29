package gui.model;

import gui.GUIController;
import gui.elements.LabelWithWidget;
import javafx.beans.InvalidationListener;
import ql.evaluation.SymbolTable;

public interface IGUIQuestion {
    LabelWithWidget render(GUIController guiController);
}
