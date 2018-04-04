package org.uva.jomi.ui.views.core;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Panel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Panel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS ));
	}
	
}
