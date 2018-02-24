package org.uva.jomi.ui.elements;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PanelElement implements BaseElement{
	
	private List<BaseElement> elements;
	
	public PanelElement() {
		this.elements = new ArrayList<BaseElement>();
	}
	
	public void addElement(BaseElement element) {
		this.elements.add(element);
	}

	@Override
	public JPanel build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS ));
		
		for(BaseElement element : this.elements) {
			panel.add(element.build());
		}
		
		return panel;
	}
	
}
