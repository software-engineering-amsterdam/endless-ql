package gui.model;

import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import ql.analysis.SymbolTable;
import qls.model.StyleSheet;

public interface GUIInterface {
    public void update(SymbolTable symbolTable);
    public void update(StyleSheet styleSheet);

    public Parent render();

    public void setChangeListener(InvalidationListener invalidationListener);
}
