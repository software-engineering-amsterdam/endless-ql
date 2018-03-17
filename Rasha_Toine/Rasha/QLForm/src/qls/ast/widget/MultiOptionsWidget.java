package qls.ast.widget;

import java.util.Collections;
import java.util.List;


public abstract class MultiOptionsWidget extends QLSWidget {
	private List<String> options;
	private String defaultOption;

	//constructor
	public MultiOptionsWidget(List<String> options, String defaultOption) {
		this.options = options;
		this.defaultOption = defaultOption;
	}

	public List<String> getOptions() {
		return Collections.unmodifiableList(options);
	}

	public String getDefaultOption() {
		return defaultOption;
	}
}