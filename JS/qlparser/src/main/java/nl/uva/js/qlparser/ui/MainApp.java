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
import nl.uva.js.qlparser.ui.panes.FormPane;
import nl.uva.js.qlparser.ui.panes.InputPane;
import nl.uva.js.qlparser.ui.panes.LogPane;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.File;
import java.util.BitSet;

public class MainApp extends Application implements ANTLRErrorListener {

    private InputPane inputPane;
    private FormPane  formPane;
    private LogPane   logger;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        inputPane = new InputPane();
        formPane  = new FormPane();
        logger    = new LogPane();

        inputPane.setMinWidth(300);
        formPane.autosize();
        logger.setMinWidth(300);

        MenuBar menuBar = getMenuBar(stage);

        HBox mainPanes = new HBox();
        mainPanes.getChildren().addAll(inputPane, formPane, logger);

        Button processButton = new Button("Process QL");
        processButton.setOnAction(e -> process());

        BorderPane buttonPane = new BorderPane();
        buttonPane.setPadding(new Insets(10, 10, 10, 10));
        buttonPane.setTop(processButton);

        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(mainPanes);
        layout.setBottom(buttonPane);

        stage.setTitle("JS QL");
        stage.setResizable(false);
        stage.setScene(new Scene(layout, 1500, 1000));
        stage.show();
    }

    // TODO
    private void process() {
        logger.clear();

        logger.log("Parsing...");
        boolean parseSuccess = true;

        try {
            // Get parse tree
        } catch (Exception e) {
            logger.log("Parse error (See System.out)");
            parseSuccess = false;
        }

        if (parseSuccess) {
            logger.log("Building AST...");

            //Check
            logger.log("Checking...");

            // Evaluate / render
            logger.log("Rendering questionnaire...");

            logger.log("Process finished");
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

    // ANTLR error handling
    @Override
    public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
                                boolean arg4, BitSet arg5, ATNConfigSet arg6) {
    }

    @Override
    public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
                                            int arg3, BitSet arg4, ATNConfigSet arg5) {
    }

    @Override
    public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
                                         int arg3, int arg4, ATNConfigSet arg5) {
    }

    @Override
    public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
                            int arg3, String arg4, RecognitionException arg5) {
        logger.log(arg5.toString());
    }

}

