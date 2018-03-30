package ql.gui.view.widgets;

import ql.gui.model.QuestionModel;
import ql.gui.view.Widget;

import javax.swing.*;
import java.math.BigInteger;

public class IntegerSpinnerWidget extends Widget {

    private final JSpinner spinner;

    public IntegerSpinnerWidget(QuestionModel questionModel) {
        super(questionModel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(
                0,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                1
        );

        JSpinner spinner = new JSpinner(spinnerModel);

        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinner);
        editor.getFormat().setGroupingUsed(false);
        spinner.setEditor(editor);

        if (questionModel.getAssignedExpression() != null) {
            spinner.setEnabled(false);
        }

        spinner.addChangeListener(e -> questionModel.changeValue(new BigInteger(spinner.getValue().toString())));

        this.spinner = spinner;
    }

    @Override
    public void updateValue() {
        this.spinner.setValue(this.getQuestionModel().getQLDataTypeValue().getValue());
    }

    @Override
    public JComponent getComponent() {
        return this.spinner;
    }
}
