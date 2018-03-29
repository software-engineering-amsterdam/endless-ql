package gui.model;

import gui.GUIController;
import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import ql.evaluation.SymbolTable;

public abstract class GUIElement {
    public abstract Parent render(GUIController guiController);
}
