//package gui;
//
//import gui.widgets.*;
//import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.css.Stylesheet;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import ql.QLFormBuilder;
//import ql.analysis.SymbolTable;
//import ql.model.Form;
//import ql.model.Question;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DraftRenderer extends Application {
//
//    // Classloader for maven resource files
//    private final ClassLoader classLoader = getClass().getClassLoader();
//
//    private Form form;
//    private Stylesheet stylesheet;
//    private SymbolTable symbolTable;
//
//    private Button selectQLFile;
//    private Button selectQLSFile;
//    private Button openForm;
//
//    private void createButtons(Stage stage) {
//        selectQLFile = new Button("Select QL file...");
//        selectQLSFile = new Button("Select QLS file...");
//
//        // Determine default QL/QLS location
//        File resourcesFile = new File(classLoader.getResource("java/example.ql").getFile());
//        File resourceDirectory = new File(resourcesFile.getParentFile().getAbsolutePath());
//
//        // Create file choosers
//        FileChooser fileChooserQL = new FileChooser();
//        FileChooser fileChooserQLS = new FileChooser();
//        fileChooserQL.setInitialDirectory(resourceDirectory);
//        fileChooserQLS.setInitialDirectory(resourceDirectory);
//
//        openForm = new Button("Open");
//
//        selectQLFile.setOnAction((event) -> {
//            File file = fileChooserQL.showOpenDialog(stage);
//            if (file != null) {
//                loadForm(file);
//            }
//        });
//        selectQLSFile.setOnAction((event) -> {
//            File file = fileChooserQLS.showOpenDialog(stage);
//            if (file != null) {
//                loadStylesheet(file);
//            }
//        });
//        openForm.setOnAction((event) -> {
//            drawForm(stage);
//        });
//    }
//
//    private void loadForm(File file) {
//        try {
//            QLFormBuilder qlFormBuilder = new QLFormBuilder();
//            form = qlFormBuilder.buildForm(new FileInputStream(file));
//            symbolTable = qlFormBuilder.getSymbolTable();
//        } catch (FileNotFoundException e) {
//            showErrorAlert(e, "QL file not found");
//        } catch (UnsupportedOperationException | IllegalArgumentException e) {
//            showErrorAlert(e, "QL file invalid");
//        } catch (IOException e) {
//            showErrorAlert(e, "IO exception while lexing form file");
//        }
//    }
//
//    private void loadStylesheet(File file){
//        // TODO
//    }
//
//    private void drawForm(Stage stage) {
//        VBox widgetPane = new VBox();
//        if(form == null){
//            showErrorAlert(new UnsupportedOperationException("No QL file"), "No (valid) QL file selected");
//        }
//
//        ChangeListener<? super String> listener = (ov, oldValue, newValue) -> {
//            System.out.println("Value changed to: " + newValue);
//        };
//
//        Map<String, Widget> questionWidgets = new HashMap<>();
//
//        for(Question question : form.questions){
//            Widget widget;
//            switch(question.type){
//                case BOOLEAN:
//                    widget = new CheckboxWidget(listener, question.name);
//                    break;
//                case DATE:
//                    widget = new DateWidget(listener, question.name);
//                    break;
//                case MONEY:
//                    widget = new MoneyWidget(listener, question.name);
//                    break;
//                case DECIMAL:
//                    widget = new DecimalWidget(listener, question.name);
//                    break;
//                case INTEGER:
//                    widget = new IntegerWidget(listener, question.name);
//                    break;
//                case STRING:
//                    widget = new StringWidget(listener, question.name);
//                    break;
//                default:
//                    showErrorAlert(new UnsupportedOperationException(""), "");
//                    return;
//            }
//            widgetPane.getChildren().add(widget.getUI());
//            questionWidgets.put(question.name, widget);
//        }
//        stage.setScene(new Scene(widgetPane));
//    }
//
//    private void showErrorAlert(Exception e, String message) {
//        e.printStackTrace();
//        Alert alert = new Alert(Alert.AlertType.ERROR, message);
//        alert.setContentText(e.getMessage());
//        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
//        alert.showAndWait();
//    }
//
//    private void draw(Stage stage){
//        HBox buttonPane = new HBox(selectQLFile, selectQLSFile, openForm);
//
//        VBox primaryPane = new VBox(buttonPane);
//        Scene primaryScene = new Scene(primaryPane);
//        stage.setScene(primaryScene);
//        stage.show();
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        createButtons(primaryStage);
//        draw(primaryStage);
//
////        VBox pane = new VBox();
////        pane.setSpacing(20);
////        pane.setPadding(new Insets(20, 20, 20, 20));
//    }
//}