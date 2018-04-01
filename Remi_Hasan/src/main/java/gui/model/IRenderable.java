package gui.model;

import gui.render.GUIController;
import javafx.scene.Parent;

public interface IRenderable {
    Parent render(GUIController guiController);
}
