package gui.model;

import gui.components.LabelWithWidget;
import gui.render.GUIController;

public interface IGUIQuestion {
    LabelWithWidget render(GUIController guiController);
}
