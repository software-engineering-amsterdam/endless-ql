package org.uva.jomi.ui.elements.panel;

import java.util.List;

import org.uva.jomi.ui.elements.BaseElement;
import org.uva.jomi.ui.elements.core.Panel;
import org.uva.jomi.ui.elements.question.Label;

public class ErrorPanel implements BaseElement {

	private List<String> errors;
	
	public ErrorPanel(List<String> errors) {
		this.errors = errors;		
	}

	@Override
	public Panel build() {
		Panel panel = new Panel();

		for(String error : this.errors) {
			Label errorLabel = new Label(error);
			panel.add(errorLabel.build());
		}

		return panel;
	}
	
	
}
