package gui.model;

import gui.render.GUIController;
import gui.elements.LabelWithWidget;

public interface IGUIQuestion {
    LabelWithWidget render(GUIController guiController);
}
