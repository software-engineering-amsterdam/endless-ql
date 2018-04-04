package qls.gui.view;

import ql.gui.controller.SubmitFormController;
import ql.gui.model.FormModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PagePanel extends JPanel {

    private JPanel innerPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private int i = 0;

    public PagePanel(String name, List<JComponent> components) {

        TitledBorder titled = new TitledBorder(name);
        this.innerPanel.setBorder(titled);

        JScrollPane scrollFrame = new JScrollPane(this.innerPanel);
        this.innerPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(800, 600));

        this.gridBagConstraints.anchor = GridBagConstraints.WEST;

        // render form questions panels
        for (JComponent component : components) {
            this.gridBagConstraints.gridy = this.i;
            this.innerPanel.add(component, this.gridBagConstraints);
            this.i++;
        }

        this.add(scrollFrame);
        this.setVisible(true);
    }

    public void enableSubmitButton(FormModel formModel) {
        // Form submit
        JButton submit = new JButton("Submit form");
        submit.addActionListener(new SubmitFormController(formModel));

        this.gridBagConstraints.gridy = this.i;
        this.gridBagConstraints.anchor = GridBagConstraints.CENTER;
        this.innerPanel.add(submit, this.gridBagConstraints);
        this.revalidate();
        this.repaint();
    }
}
