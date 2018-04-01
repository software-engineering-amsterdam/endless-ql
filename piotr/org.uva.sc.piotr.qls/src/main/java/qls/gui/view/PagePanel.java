package qls.gui.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PagePanel extends JPanel {

    public PagePanel(String name, List<JComponent> components) {

        JPanel innerPanel = new JPanel(new GridBagLayout());

        TitledBorder titled = new TitledBorder(name);
        innerPanel.setBorder(titled);

        JScrollPane scrollFrame = new JScrollPane(innerPanel);
        innerPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(800, 600));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        // render form questions panels
        int i = 0;
        for (JComponent component: components) {
            gridBagConstraints.gridy = i;
            innerPanel.add(component, gridBagConstraints);
            i++;
        }

//        // Form submit
//        JButton submit = new JButton("Submit form");
//        submit.addActionListener(new SubmitFormController(formModel));
//
//        gridBagConstraints.gridy = i;
//        innerPanel.add(submit, gridBagConstraints);

        this.add(scrollFrame);

        this.setVisible(true);
    }
}
