package org.uva.jomi.ui.elements.core;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Frame extends JFrame {

	public Frame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(400, 600));
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		this.pack();
	}

}
