package nl.uva.js.qlparser.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

@SuppressWarnings("restriction")
public class QuestionnairePane extends BorderPane {

	public QuestionnairePane() {
		super();

		Label title = new Label("Output");
		title.setPadding(new Insets(0, 0, 5, 0));
	
		TabPane tabs = new TabPane();
		tabs.setMinWidth(900);
		Tab questionnaireTab, htmlTab;

		WebView questionnaire = new WebView();

		questionnaireTab = new Tab();
		questionnaireTab.setText("Questionnaire");
		questionnaireTab.setClosable(false);
		questionnaireTab.setContent(questionnaire);

		TextArea rawHtml = new TextArea();
		rawHtml.setEditable(false);

		htmlTab = new Tab();
		htmlTab.setText("Html");
		htmlTab.setClosable(false);
		htmlTab.setContent(rawHtml);
	
		tabs.getTabs().addAll(questionnaireTab,htmlTab);

		this.setTop(title);
		this.setCenter(tabs);
		this.setPadding(new Insets(5, 5, 5, 5));
	}
}
