package tool;

import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import domain.model.ast.FormNode;
import domain.Utilities;
import domain.model.ast.ASTNode;
import domain.model.ast.IfASTNode;
import domain.model.ast.QuestionASTNode;
import domain.model.variable.Variable;
import domain.visitor.UIVisitor;
import domain.visitor.Visitor;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QL.QLLoader;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

public class ToolController implements Initializable, Consumer {

    @FXML
    private TextArea taSourceCodeQL;

    @FXML
    private TextArea taSourceCodeQLS;

    @FXML
    private ListView<Row> lvQuestionnaire;

    @FXML
    private Button btnBuild;

    @FXML
    private Label lblErrorField;

    private FormNode formNode = null;

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
        String qlSource = taSourceCodeQL.getText();

        if(qlSource.isEmpty()){
            showAlertBox("Please import or add QL code");
            return;
        }

        String qlsSource = taSourceCodeQLS.getText();

        if(qlsSource.isEmpty()){
            System.out.println("QLS not set, fall back to default styling");
        }

        lvQuestionnaire.getItems().clear();

        // Parse input field and create AST
        CharStream stream = CharStreams.fromString(qlSource);
        FormLexer lexer = new FormLexer(stream);

        FormParser parser = new FormParser(new CommonTokenStream(lexer));

        parser.setErrorHandler(new BailErrorStrategy());
        parser.addErrorListener(new DialogErrorListener(lblErrorField));

        FormParser.FormBuilderContext tree = parser.formBuilder();
        QLLoader loader = new QLLoader();
        ParseTreeWalker.DEFAULT.walk(loader, tree);

        this.formNode = loader.getFormNode();

        List<ASTNode> astNodes = this.formNode.getASTNodes();

        List<QuestionASTNode> questions = getAllQuestions(astNodes);
        drawQuestions(questions);

        printInfoMessage("Build successful");
    }

    private void printInfoMessage(String message){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        lblErrorField.setTooltip(new Tooltip(sdf.format(cal.getTime())));
        lblErrorField.setText(message);
    }

    private void drawQuestions(List<QuestionASTNode> questionASTNodes){
        Visitor uiVisitor = new UIVisitor();
        lvQuestionnaire.getItems().clear();

        this.formNode.evaluateIfs();

        for(QuestionASTNode qn : questionASTNodes){
            String questionText = qn.getText();
            Variable qv = qn.getVariable();

            Node n = qv.getRelatedUIElement(uiVisitor);

            JavaFxObservable.actionEventsOf(n)
                    .subscribe(this::accept);

            if(n instanceof TextField){
                TextField tf = (TextField) n;

                JavaFxObservable.changesOf(tf.focusedProperty())
                        .map(Change::getNewVal)
                        .filter(aBoolean -> !aBoolean)
                        .subscribe(this::accept);
            }

            Row r = new QuestionRow(questionText, n);
            r.setDisable(qn.isDisabled());
            lvQuestionnaire.getItems().add(r);
        }

        JavaFxObservable.updatesOf(lvQuestionnaire.getItems()).subscribe(rows -> System.out.println("R "+rows));
    }



    private List<QuestionASTNode> getAllQuestions(List<ASTNode> nodes){
        List<QuestionASTNode> visQuestion = new ArrayList<>();
        for(ASTNode n : nodes){

            if(n instanceof QuestionASTNode){
                visQuestion.add((QuestionASTNode) n);
                continue;
            }

            IfASTNode ifASTNode = (IfASTNode) n;

            visQuestion.addAll(ifASTNode.getQuestionNodes());
            visQuestion.addAll(ifASTNode.getElseNodes());
        }

        return visQuestion;
    }

    public void importQLSFile(ActionEvent event) {
        FileChooser fileChooser = getFileChooser();

        Stage s = new Stage();
        File selectedFile = fileChooser.showOpenDialog(s);

        if (selectedFile == null) {
            return;
        }

        Optional<String> qlsText = Utilities.readFile(selectedFile.getAbsolutePath());

        qlsText.ifPresentOrElse(
                text -> {
                    taSourceCodeQLS.setText(text);
                    printInfoMessage("Import "+ selectedFile.getName() +"successful");
                },
                () -> showAlertBox("Could not read file.")
        );
    }

    /**
     * Invoked by the 'Import' button action, import .QL file
     * @param event that kicked of the invocation
     */
    public void importQLFile(ActionEvent event) {
        FileChooser fileChooser = getFileChooser();

        Stage s = new Stage();
        File selectedFile = fileChooser.showOpenDialog(s);

        if (selectedFile == null) {
            return;
        }

        Optional<String> qlText = Utilities.readFile(selectedFile.getAbsolutePath());

        qlText.ifPresentOrElse(
                text -> {
                    taSourceCodeQL.setText(text);
                    printInfoMessage("Import "+ selectedFile.getName() +"successful");
                },
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
                new FileChooser.ExtensionFilter("Questionnaire Language File (*.qls)", "*.qls"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        return fileChooser;
    }

    @Override
    public void accept(Object event) {
        System.out.println("Redraw tree yo");
        List<QuestionASTNode> questions = getAllQuestions(this.formNode.getASTNodes());
        drawQuestions(questions);
    }
}
