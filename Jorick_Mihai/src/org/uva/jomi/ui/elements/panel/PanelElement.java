package org.uva.jomi.ui.elements.panel;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.core.Panel;

public class PanelElement implements BaseElement{

	private List<BaseElement> elements;

	public PanelElement() {
		this.elements = new ArrayList<BaseElement>();
	}

	public void addElement(BaseElement element) {
		this.elements.add(element);
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();

		for(BaseElement element : this.elements) {
			panel.add(element.build());
		}

		return panel;
	}

}
