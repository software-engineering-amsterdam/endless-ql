package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import org.uva.sea.gui.model.BaseQuestionModel;

public abstract class AbstractWidgetFactory {

    public abstract Control createWidget(BaseQuestionModel questionModel);
}
