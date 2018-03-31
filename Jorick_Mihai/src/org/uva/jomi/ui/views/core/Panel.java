package org.uva.jomi.ui.views.core;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Panel extends JPanel {

	public Panel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ));
	}
	
}
