package gui.model;

import gui.render.GUIController;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class GUIFormWithStyling extends GUIForm implements IRenderable {

    private List<GUIPage> pages;

    public GUIFormWithStyling(String identifier, List<GUIQuestion> questions, List<GUIPage> pages) {
        super(identifier, questions);
        this.pages = pages;
    }

    public List<GUIPage> getPages() {
        return pages;
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

        // Bind tabPane height to window height
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        return tabPane;
    }
}
