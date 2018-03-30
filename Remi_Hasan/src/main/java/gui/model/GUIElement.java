package gui.model;

import gui.render.GUIController;
import javafx.scene.Parent;

public abstract class GUIElement implements IRenderable {
    public abstract Parent render(GUIController guiController);
}
