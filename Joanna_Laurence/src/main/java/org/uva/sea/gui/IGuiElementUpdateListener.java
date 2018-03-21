package org.uva.sea.gui;

import javafx.scene.control.Control;
import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public interface IGuiElementUpdateListener {
    void updateGuiVariable(Control control, String identifier, Value value);
}
