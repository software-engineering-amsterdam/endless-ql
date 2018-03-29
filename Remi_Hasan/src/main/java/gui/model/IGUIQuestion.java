package gui.model;

import gui.elements.LabelWithWidget;
import gui.render.GUIController;

public interface IGUIQuestion {
    LabelWithWidget render(GUIController guiController);
}
