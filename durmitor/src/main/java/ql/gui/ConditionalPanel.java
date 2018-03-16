package ql.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ConditionalPanel extends JPanel {

    private static final long serialVersionUID = -6489797720369457759L;
    
    public ConditionalPanel(String name) {
        super();
        setName(name);
        setLayout(
                new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }
}
