package gui.model;

import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import ql.analysis.SymbolTable;
import qls.model.StyleSheet;

import java.util.List;

public class GUIForm extends ScrollPane implements GUIInterface {
    public final String identifier;
    public final List<GUIInterface> children;

    public GUIForm(String identifier, List<GUIInterface> children) {
        this.identifier = identifier;
        this.children = children;

        VBox vBox = new VBox();
        for (GUIInterface child : children) {
            vBox.getChildren().add(child.render());
        }
        this.setContent(vBox);

        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public void update(SymbolTable symbolTable) {
        for (GUIInterface child : children) {
            child.update(symbolTable);
        }
    }

    @Override
    public void update(StyleSheet styleSheet) {
        for (GUIInterface child : children) {
            child.update(styleSheet);
        }
    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        for (GUIInterface child : children) {
            child.setChangeListener(invalidationListener);
        }
    }

}