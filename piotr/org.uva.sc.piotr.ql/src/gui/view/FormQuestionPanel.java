package gui.view;

import ast.model.expressions.Expression;
import gui.model.FormQuestion;
import gui.view.widgets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormQuestionPanel extends JPanel {
    private final Widget widget;
    private final FormQuestion formQuestion;

    public FormQuestionPanel(FormQuestion formQuestion) {
        super(new GridBagLayout());
        formQuestion.setPanel(this);

        this.formQuestion = formQuestion;
        this.widget = createDefaultWidget(formQuestion);
        JLabel labelComponent = new JLabel(formQuestion.getLabel());

        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        this.add(labelComponent, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        this.add(this.widget.getComponent(), gridBagConstraints);

        if (formQuestion.getVisibility() != null)
            this.setVisible(formQuestion.getVisibility().getBooleanValue());
    }

    public FormQuestion getFormQuestion() {
        return formQuestion;
    }

    public void refreshVisibility() {
        if (this.formQuestion.getVisibility() != null) {
            this.setVisible(this.formQuestion.getVisibility().getBooleanValue());
        }
    }

    public void refreshValue() {
        if (this.formQuestion.getValue() != null) {
            this.widget.updateValue();
        }
    }

    private static Widget createDefaultWidget(FormQuestion formQuestion) {

        Expression.DataType questionDataType = formQuestion.getOriginalDataTypeDeclaration().toDataType();

        switch (questionDataType) {
            case DECIMAL:
                // if decimal is originally declared as "money", then use a widget with currency sign
                if (formQuestion.getOriginalDataTypeDeclaration().getIdentifier().equals("money")) {
                    return new MoneyFieldWidget(formQuestion);
                }
                return new DecimalFieldWidget(formQuestion);
            case INTEGER:
                return new IntegerSpinnerWidget(formQuestion);
            case BOOLEAN:
                //return new BooleanCheckboxWidget(this.formQuestion);
                return new BooleanRadioWidget(formQuestion);
        }
        // string and any other
        return new TextFieldWidget(formQuestion);
    }
}
