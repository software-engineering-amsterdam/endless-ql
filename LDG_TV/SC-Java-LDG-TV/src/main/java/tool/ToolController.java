package tool;

import antlr.FormLexer;
import antlr.FormParser;
import domain.FormData;
import domain.FormNode;
import domain.Utilities;
import domain.model.Question;
import domain.model.variable.Variable;
import domain.model.visitor.UIVisitor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QLLoader;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.net.URL;
import java.util.*;

public class ToolController implements Initializable {

    @FXML
    private TextArea taSourceCode;

    @FXML
    private ListView<Row> lvQuestionnaire;

    private List<Row> data = new ArrayList<>();

    public ToolController() {
        System.out.println("Class initialized");
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Pane initialized");
    }

    /**
     * Invoked by the 'build' button action, to generate the questionnaire based on the written QL
     * @param event that kicked of the invocation
     */
    public void generateQuestionnaire(ActionEvent event) {
        String qlSource = taSourceCode.getText();

//        if(qlSource.isEmpty()){
//            showAlertBox("Please import or add QL code");
//            return;
//        }

        lvQuestionnaire.getItems().clear();

        CharStream stream = CharStreams.fromString(qlSource);
        FormLexer lexer = new FormLexer(stream);

        FormParser parser = new FormParser(new CommonTokenStream(lexer));

        FormParser.FormBuilderContext tree = parser.formBuilder();
        QLLoader loader = new QLLoader();
        ParseTreeWalker.DEFAULT.walk(loader, tree);

        FormNode node = loader.getFormNode();
        FormData data = node.getFormData();


//
//
//        List<Question> qs = data.getAllQuestions();
//        UIVisitor v = new UIVisitor();
//        for (Question q : qs) {
//            Variable qv =  q.getVariable();
//            String qText = q.getText();
//            Node answerNode = qv.getRelatedUIElement(v);
//            lvQuestionnaire.getItems().add(new QuestionRow(qText, answerNode));
//
//        }


        this.lvQuestionnaire.getItems().setAll(dummyRows());
    }


    private List<Row> dummyRows(){
        QuestionRow row1_1 = new QuestionRow("Man or woman", new TextField(), true);
        QuestionRow row1_2 = new QuestionRow("Where do you work?", new TextField(), true);

        IfRow row1 = new IfRow("Older than 18?", new CheckBox(), Arrays.asList(row1_1, row1_2));

        row1_1.visibleProperty().bindBidirectional(row1.selectedProp());
        row1_2.visibleProperty().bindBidirectional(row1.selectedProp());

        return Arrays.asList(row1, row1_1, row1_2);
    }

    /**
     * Invoked by the 'Import' button action, import .QL file
     * @param event that kicked of the invocation
     */
    public void importQLFile(ActionEvent event) {
        FileChooser fileChooser = getFileChooser();

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile == null) {
            return;
        }

        Optional<String> qlText = Utilities.readFile(selectedFile.getAbsolutePath());

        qlText.ifPresentOrElse(
                text -> taSourceCode.setText(text),
                () -> showAlertBox("Could not read file.")
        );
    }

    private void showAlertBox(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);

        alert.showAndWait();
    }

    private FileChooser getFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QL File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Questionnaire Language File (*.ql)", "*.ql"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        return fileChooser;
    }
}
