package org.uva.sea.gui.widget;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.uva.sea.gui.FormController;
import org.uva.sea.gui.model.BaseQuestionModel;

public class SpinnerWidget implements Widget {
    @Override
    public Control draw(BaseQuestionModel questionModel, FormController controller) {
        //TODO: get properties from Widget
        Spinner<Integer> spinner = new Spinner<Integer>();

        final int initialValue = 3;

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, initialValue);

        spinner.setValueFactory(valueFactory);

        return spinner;
    }
}
