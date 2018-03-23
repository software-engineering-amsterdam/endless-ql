package gui.model;

import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import ql.analysis.SymbolTable;
import qls.model.StyleSheet;

public class GUILabel extends Label implements GUIInterface {

    public GUILabel(String label) {
        super(label);
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void update(SymbolTable symbolTable) {

    }

    @Override
    public void update(StyleSheet styleSheet) {

    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {

    }
}
