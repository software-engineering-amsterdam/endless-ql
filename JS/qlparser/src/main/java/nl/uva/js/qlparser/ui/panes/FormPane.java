package nl.uva.js.qlparser.ui.panes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class FormPane extends BorderPane {

    private WebView  webView;
    private TextArea htmlArea;

    public FormPane() {
        super();

        Label title = new Label("Output");
        title.setPadding(new Insets(0, 0, 5, 0));

        TabPane tabs = new TabPane();
        tabs.setMinWidth(900);
        Tab questionnaireTab, htmlTab;

        webView = new WebView();

        questionnaireTab = new Tab();
        questionnaireTab.setText("Questionnaire");
        questionnaireTab.setClosable(false);
        questionnaireTab.setContent(webView);

        htmlArea = new TextArea();
        htmlArea.setEditable(false);

        htmlTab = new Tab();
        htmlTab.setText("Html");
        htmlTab.setClosable(false);
        htmlTab.setContent(htmlArea);

        tabs.getTabs().addAll(questionnaireTab, htmlTab);

        this.setTop(title);
        this.setCenter(tabs);
        this.setPadding(new Insets(5, 5, 5, 5));
    }

    public void setHtml(String html) {
        webView.getEngine().loadContent(html);
        htmlArea.setText(html);
    }
//
//    public Document getDocument() {
//        return webView.getEngine().getDocument();
//    }
//
//    private void setCss(String property, String value, Node node, Document doc) {
//        Attr styleAttr = (Attr) node.getAttributes().getNamedItem("style");
//        if (styleAttr == null) {
//            styleAttr = doc.createAttribute("style");
//            styleAttr.setValue(property + ": " + value);
//        } else {
//            styleAttr.setValue(styleAttr.getValue() + "; " + property + ": " + value);
//        }
//        node.getAttributes().setNamedItem(styleAttr);
//    }


}
