package gui.model;

import gui.render.GUIController;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class GUIFormWithStyling extends GUIForm {

    private List<GUIPage> pages;

    public GUIFormWithStyling(String identifier, List<GUIPage> pages) {
        super(identifier, null);
        this.pages = pages;
    }

    @Override
    public Parent render(GUIController guiController) {
        TabPane tabPane = new TabPane();

        for (GUIPage page : pages) {
            Tab tab = new Tab();
            tab.setText(page.getIdentifier());
            tab.setClosable(false);
            tab.setContent(page.render(guiController));
            tabPane.getTabs().add(tab);
        }

        // Update all widgets to set them to their initial values
        guiController.updateQuestionWidgets();

        return tabPane;
    }
}
