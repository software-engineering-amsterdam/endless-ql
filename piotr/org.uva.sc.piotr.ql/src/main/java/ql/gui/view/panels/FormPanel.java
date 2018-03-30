package ql.gui.view.panels;

import ql.gui.controller.SubmitFormController;
import ql.gui.model.FormModel;
import ql.gui.view.FormView;
import ql.gui.view.QuestionView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class FormPanel extends JPanel {

    public FormPanel(String name, FormModel formModel, FormView formView) {

        JPanel innerPanel = new JPanel(new GridBagLayout());

        TitledBorder titled = new TitledBorder(name);
        innerPanel.setBorder(titled);

        JScrollPane scrollFrame = new JScrollPane(innerPanel);
        innerPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(600, 800));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        // render form questions panels
        int i = 0;
        for (QuestionView questionView : formView.getQuestionViews()) {
            gridBagConstraints.gridy = i;
            innerPanel.add(questionView, gridBagConstraints);
            i++;
        }

        // Form submit
        JButton submit = new JButton("Submit form");
        submit.addActionListener(new SubmitFormController(formModel));

        gridBagConstraints.gridy = i;
        innerPanel.add(submit, gridBagConstraints);

        this.add(scrollFrame);
    }
}
