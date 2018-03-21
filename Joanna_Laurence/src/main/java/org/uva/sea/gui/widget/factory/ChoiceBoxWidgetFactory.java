package org.uva.sea.gui.widget.factory;

import javafx.scene.control.Control;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;
import org.uva.sea.gui.widget.factory.visitor.ChoiceBoxFromValueVisitor;

public class ChoiceBoxWidgetFactory extends AbstractWidgetFactory {
    final FormController controller;

    public ChoiceBoxWidgetFactory(FormController controller) {
        this.controller = controller;
    }

    @Override
    public Control createWidget(BaseQuestionModel questionModel) {
        return questionModel.accept(new ChoiceBoxFromValueVisitor(this.controller));
    }
}