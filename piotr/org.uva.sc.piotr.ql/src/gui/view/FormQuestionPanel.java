package gui.view;

import ast.model.expressions.Expression;
import gui.model.FormQuestionHolder;
import gui.view.widgets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FormQuestionPanel extends JPanel {
    private JLabel labelComponent;
    private Widget widget;
    private FormQuestionHolder formQuestionHolder;

    public FormQuestionPanel(FormQuestionHolder formQuestionHolder) {
        super(new GridBagLayout());
        formQuestionHolder.setPanel(this);

        this.formQuestionHolder = formQuestionHolder;
        this.labelComponent = new JLabel(formQuestionHolder.getLabel());
        this.widget = this.createDefaultWidget();

        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        this.add(this.labelComponent, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        this.add(this.widget.getComponent(), gridBagConstraints);

        if (formQuestionHolder.getVisibilityHolder() != null)
            this.setVisible(formQuestionHolder.getVisibilityHolder().getBooleanValue());
    }

    public FormQuestionHolder getFormQuestionHolder() {
        return formQuestionHolder;
    }

    private Widget createDefaultWidget() {

        Expression.DataType questionDataType = this.formQuestionHolder.getOriginalDataTypeDeclaration().toDataType();

        if (questionDataType == Expression.DataType.DECIMAL) {
            // if decimal is originally declared as "money", then use a widget with currency sign
            if (this.formQuestionHolder.getOriginalDataTypeDeclaration().getIdentifier().equals("money")) {
                return new MoneyFieldWidget(this.formQuestionHolder);
            }
            return new DecimalFieldWidget(this.formQuestionHolder);
        } else if (questionDataType == Expression.DataType.INTEGER) {
            return new IntegerSpinnerWidget(this.formQuestionHolder);
        } else if (questionDataType == Expression.DataType.BOOLEAN) {
            return new BooleanCheckboxWidget(this.formQuestionHolder);
        }
        // string and any other
        return new TextFieldWidget(this.formQuestionHolder);
    }

    public void refreshVisibility() {
        if (this.formQuestionHolder.getVisibilityHolder() != null) {
            System.out.println("@@ Updating visibility of " + this.formQuestionHolder.getVariableName() + " to " + this.formQuestionHolder.getVisibilityHolder().getBooleanValue());
            this.setVisible(this.formQuestionHolder.getVisibilityHolder().getBooleanValue());
        }
    }

    public void refreshValue() {
        if (this.formQuestionHolder.getValueHolder() != null) {
            this.widget.updateValue();
            // change value
            //this.widget.getComponent().(this.formQuestionHolder.getVisibilityHolder().getBooleanValue());
        }
    }
}
