package gui.model;

import gui.elements.LabelWithWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import ql.evaluation.SymbolTable;
import qls.model.Page;
import qls.model.StyleSheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIFormWithStyling extends GUIForm {

    private List<GUIPage> pages;

    // TODO: improve this
    public static Map<GUIQuestion, LabelWithWidget> guiWidgetsMap = new HashMap<>();

    public GUIFormWithStyling(String identifier, List<GUIPage> pages) {
        super(identifier, null);
        this.pages = pages;
    }

    @Override
    public Parent render(SymbolTable symbolTable) {
        TabPane tabPane = new TabPane();

        // Listener that is notified by UI widget input event
        InvalidationListener allWidgetsListener = observable -> {
            this.updateRenderedQuestions(guiWidgetsMap, symbolTable);
        };

        for(GUIPage page : pages) {
            Tab tab = new Tab();
            tab.setText(page.getIdentifier());
            tab.setClosable(false);
            tab.setContent(page.render(symbolTable, allWidgetsListener));
            tabPane.getTabs().add(tab);
        }

        // Update question values/visibility for the first time
        this.updateRenderedQuestions(guiWidgetsMap, symbolTable);

        return tabPane;
    }
}
