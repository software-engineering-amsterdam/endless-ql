package nl.uva.js.qlparser.ui.panes;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class InputPane extends BorderPane {
	private TextArea content;

	public InputPane() {
		super();

		Label title = new Label("Questionnaire Language input");
		title.setPadding(new Insets(5, 5, 5, 5));

		content = new TextArea();

		this.setTop(title);
		this.setCenter(content);

		String file = "input_files/ql_input.jsql";
		File qlFile = new File(file);
		setTextFromFile(qlFile);
	}

	public void setTextFromFile(File file) {
		try {
			this.setText(new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset()));
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public void setText(String text) {
		this.content.setText(text);
	}

	public String getText() {
		return content.getText();
	}
}
