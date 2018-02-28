package nl.uva.js.qlparser.ui;

import javafx.scene.control.TextArea;

@SuppressWarnings("restriction")
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
