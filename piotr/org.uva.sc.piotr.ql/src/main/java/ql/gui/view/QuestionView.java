package ql.gui.view;

import ql.ast.model.declarations.TypeDeclaration;
import ql.gui.model.QuestionModel;
import ql.gui.view.widgets.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QuestionView extends JPanel {
    private final Widget widget;
    private final QuestionModel questionModel;

    public QuestionView(QuestionModel questionModel) {
        super(new GridBagLayout());
        questionModel.setQuestionView(this);

        this.questionModel = questionModel;
        this.widget = createDefaultWidget(questionModel);
        JLabel labelComponent = new JLabel(questionModel.getQuestionLabel());

        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        gridBagConstraints.gridy = 0;
        this.add(labelComponent, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        this.add(this.widget.getComponent(), gridBagConstraints);

        this.setVisible(questionModel.getVisibility());
    }

    public QuestionModel getQuestionModel() {
        return questionModel;
    }

    public void refreshVisibility() {
        if (this.questionModel.getVisibility() != null) {
            this.setVisible(this.questionModel.getVisibility());
        }
    }

    public void refreshValue() {
        if (this.questionModel.getQLDataTypeValue() != null) {
            this.widget.updateValue();
        }
    }

    private static Widget createDefaultWidget(QuestionModel questionModel) {

        TypeDeclaration originalDataTypeDeclaration = questionModel.getOriginalDataTypeDeclaration();

        switch (originalDataTypeDeclaration.toDataType()) {
            case DECIMAL:
                // if decimal is originally declared as "money", then use a widget with currency sign
                if (originalDataTypeDeclaration.getIdentifier().equals("money")) {
                    return new MoneyFieldWidget(questionModel);
                }
                return new DecimalFieldWidget(questionModel);
            case INTEGER:
                return new IntegerSpinnerWidget(questionModel);
            case BOOLEAN:
                //return new BooleanCheckboxWidget(this.questionModel);
                return new BooleanRadioWidget(questionModel);
        }
        // string and any other
        return new TextFieldWidget(questionModel);
    }

}
