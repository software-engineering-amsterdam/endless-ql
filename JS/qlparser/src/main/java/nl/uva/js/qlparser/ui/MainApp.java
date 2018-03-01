package nl.uva.js.qlparser.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.uva.js.qlparser.exceptions.ParseException;
import nl.uva.js.qlparser.logic.FormBuilder;
import nl.uva.js.qlparser.models.expressions.Form;
import nl.uva.js.qlparser.ui.panes.FormPane;
import nl.uva.js.qlparser.ui.panes.InputPane;
import nl.uva.js.qlparser.ui.panes.LogPane;

import java.io.File;

public class MainApp extends Application {

    private LogPane logPane;
    private InputPane inputPane;
    private FormPane  formPane;


    static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JS QL");
        stage.setResizable(false);
        stage.setScene(new Scene(buildLayout(stage), 1500, 1000));
        stage.show();
    }

    private BorderPane buildLayout(Stage stage) {
        inputPane = new InputPane();
        formPane  = new FormPane();
        logPane   = new LogPane();

        inputPane.setMinWidth(300);
        formPane.autosize();
        logPane.setMinWidth(300);

        MenuBar menuBar = getMenuBar(stage);

        HBox mainPanes = new HBox();
        mainPanes.getChildren().addAll(inputPane, formPane, logPane);

        Button processButton = new Button("Process QL");
        processButton.setOnAction(e -> processQL());

        BorderPane buttonPane = new BorderPane();
        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        buttonPane.setTop(processButton);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(mainPanes);
        layout.setBottom(buttonPane);

        return layout;
    }

    // TODO
    private void processQL() {
        logPane.clear();

        logPane.log("Parsing...");

        try {
            Form form = FormBuilder.parseFormFromString(inputPane.getText());
            Form form = FormBuilder.parseFormFromString(inputPane.getText());

            ArrayList<String> questions = new ArrayList<>();

            for (FormExpression expression : form.getFormExpressions()) {
                if (expression instanceof Question) {
                    questions.add(UIBuilder.getTextInput((Question) expression));
                }
            }
            String html =
                    "<head>"
                    + "<style type=\"text/css\">"
                    + "label {"
                    +   "display: inline-block;"
                    +   "width:200px;"
                    +   "text-align: left;"
                    +  "}"
                    + "</style>"
                    + "</head>"
                    +"<form>" + String.join("<br>", questions) + "</form>";
            formPane.setHtml(html);

            logger.log("Building AST...");

            //Check
            logger.log("Checking...");

            // Evaluate / render
            logPane.log("Rendering questionnaire...");

            logPane.log("Process finished");
        } catch (ParseException e) {
            logPane.log(e.getMessage());
        }
    }

    private MenuBar getMenuBar(Stage stage) {
        MenuBar  menuBar  = new MenuBar();
        Menu     fileMenu = new Menu("File");

        MenuItem loadQl   = new MenuItem("Load QL from file");
        loadQl.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load QL file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JS QL", "*.jsql"));

            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                inputPane.setTextFromFile(file);
            }
        });

        MenuItem saveQl = new MenuItem("Save QL to file");
        saveQl.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save QL to file");
            fileChooser.setInitialFileName("myQuestionnaire.jsql");

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                // TODO
            }
        });

        MenuItem exportHtml = new MenuItem("Export questionnaire to html file");
        exportHtml.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export questionnaire to html file");
            fileChooser.setInitialFileName("myQuestionnaire.html");

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                // TODO
            }
        });

        MenuItem exportAnswers = new MenuItem("Export answers to PDF file");
        exportAnswers.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Export answers to PDF file");
            fileChooser.setInitialFileName("answers.pdf");

            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                // TODO
            }
        });

        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> Platform.exit());

        fileMenu.getItems().addAll(loadQl, saveQl, new SeparatorMenuItem(),
                exportHtml, exportAnswers, new SeparatorMenuItem(), quit);

        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }
}

