package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;

public interface Widget {
    Control draw(BaseQuestionModel questionModel, FormController controller);
}
