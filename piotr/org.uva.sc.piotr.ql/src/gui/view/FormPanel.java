package gui.view;

import ast.model.expressions.Expression;
import gui.model.FormQuestion;
import gui.view.widgets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormPanel extends JPanel {
    private JLabel labelComponent;
    private Widget widget;
    private FormQuestion formQuestion;

    public FormPanel(FormQuestion formQuestion) {
        super(new GridBagLayout());
        this.formQuestion = formQuestion;
        this.labelComponent = new JLabel(formQuestion.getLabel());

        this.widget = this.createDefaultWidget();

        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        this.add(this.labelComponent, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        this.add(this.widget.getComponent(), gridBagConstraints);
    }

    public FormQuestion getFormQuestion() {
        return formQuestion;
    }

    private Widget createDefaultWidget() {

        Expression.DataType questionDataType = this.formQuestion.getOriginalDataTypeDeclaration().toDataType();

        if (questionDataType == Expression.DataType.DECIMAL) {
            // if decimal is originally declared as "money", then use a widget with currency sign
            if (this.formQuestion.getOriginalDataTypeDeclaration().getIdentifier().equals("money")) {
                return new MoneyFieldWidget(this.formQuestion);
            }
            return new DecimalFieldWidget(this.formQuestion);
        } else if (questionDataType == Expression.DataType.INTEGER) {
            return new IntegerSpinnerWidget(this.formQuestion);
        } else if (questionDataType == Expression.DataType.BOOLEAN) {
            return new BooleanCheckboxWidget(this.formQuestion);
        }
        // string and any other
        return new TextFieldWidget(this.formQuestion);
    }
}
