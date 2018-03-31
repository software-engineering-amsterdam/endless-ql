package org.uva.jomi.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ui.views.core.Panel;

public class PanelElement implements ViewGenerator{

	private List<ViewGenerator> elements;

	public PanelElement() {
		this.elements = new ArrayList<ViewGenerator>();
	}

	public void addElement(ViewGenerator element) {
		this.elements.add(element);
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();

		for(ViewGenerator element : this.elements) {
			panel.add(element.build());
		}

		return panel;
	}

}
