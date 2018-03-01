package nl.uva.js.qlparser.ui.panes;

import javafx.scene.control.TextArea;

public class LogPane extends TextArea {

	public LogPane() {
		super();
		setEditable(false);
	}

	public void clear() {
		this.setText("");
	}
	public void log(String line) {
		this.setText( this.getText() + "\n" + line);
	}
}
