package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import org.uva.sea.gui.newImpl.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;

public abstract class Widget {

    public static final double TEXT_WIDTH = 100.0;
    private final BaseQuestionModel questionModel;
    private final FormController controller;

    public Widget(BaseQuestionModel questionModel, FormController controller) {
        this.questionModel = questionModel;
        this.controller = controller;
    }

}
