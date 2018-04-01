package org.uva.jomi.ui.views;

import java.util.List;

import org.uva.jomi.ui.views.core.Panel;

public class ErrorPanel implements ViewGenerator {

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
