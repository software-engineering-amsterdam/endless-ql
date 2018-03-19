package tool;

import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import domain.FormNode;
import domain.Utilities;
import domain.model.ASTNode;
import domain.model.IfASTNode;
import domain.model.QuestionASTNode;
import domain.model.variable.Variable;
import domain.visitor.UIVisitor;
import domain.visitor.Visitor;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QLLoader;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;

public class ToolController implements Initializable, Consumer<ActionEvent> {

    @FXML
    private TextArea taSourceCode;

    @FXML
    private ListView<Row> lvQuestionnaire;

    @FXML
    private Button btnBuild;

    private List<Row> data = new ArrayList<>();

    public ToolController() {
        System.out.println("Class initialized");
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Pane initialized");

        Observable<ActionEvent> btnEvents = JavaFxObservable.eventsOf(btnBuild, ActionEvent.ACTION);



        btnEvents.subscribe(actionEvent -> System.out.println("Action: "+actionEvent), throwable -> {
            System.out.println(throwable);
        });
    }

    /**
     * Invoked by the 'build' button action, to generate the questionnaire based on the written QL
     * @param event that kicked of the invocation
     */
    public void generateQuestionnaire(ActionEvent event) {
        String qlSource = taSourceCode.getText();

        if(qlSource.isEmpty()){
            showAlertBox("Please import or add QL code");
            return;
        }

        lvQuestionnaire.getItems().clear();

        // Parse input field and create AST
        CharStream stream = CharStreams.fromString(qlSource);
        FormLexer lexer = new FormLexer(stream);

        FormParser parser = new FormParser(new CommonTokenStream(lexer));

        FormParser.FormBuilderContext tree = parser.formBuilder();
        QLLoader loader = new QLLoader();
        ParseTreeWalker.DEFAULT.walk(loader, tree);

        FormNode node = loader.getFormNode();

        List<ASTNode> astNodes = node.getASTNodes();

        List<QuestionASTNode> questions = getAllQuestions(astNodes);


        drawQuestions(questions);


        System.out.println(node);


//        for (ASTNode n : node.getASTNodes()){
//
//            if(!(n instanceof QuestionASTNode)){
//                break;
//            }
//
//            QuestionASTNode qn = (QuestionASTNode) n;
//
//            Variable qv = qn.getVariable();
//            String qt = qn.getText();
//
//            if(qv instanceof BooleanVariable){
//                CheckBox cb = new CheckBox();
//
//                cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
//                    qv.setValue(new BooleanValue(newValue));
//                    System.out.println(qn.getText() + " " + qv.getValue().getValue());
//                });
//
//                lvQuestionnaire.getItems().add(new QuestionRow(qt, cb, false));
//                continue;
//            }
//            Node answerNode = qv.getRelatedUIElement(v);
//
//            lvQuestionnaire.getItems().add(new QuestionRow(qt, answerNode, false));
//        }

        //this.lvQuestionnaire.getItems().setAll(dummyRows());
    }

    private void drawQuestions(List<QuestionASTNode> questionASTNodes){
        Visitor uiVisitor = new UIVisitor();

        for(QuestionASTNode qn : questionASTNodes){
            String questionText = qn.getText();
            System.out.println("QUI: " + questionText);
            Variable qv = qn.getVariable();

            Node n = qv.getRelatedUIElement(uiVisitor);

            JavaFxObservable.actionEventsOf(n)
                    .subscribe(this::accept);

            Row r = new QuestionRow(questionText, n);
            r.setDisable(qn.isDisabled());
            lvQuestionnaire.getItems().add(r);
        }
    }



    private List<QuestionASTNode> getAllQuestions(List<ASTNode> nodes){
        List<QuestionASTNode> visQuestion = new ArrayList<>();
        for(ASTNode n : nodes){

            if(n instanceof QuestionASTNode){
                visQuestion.add((QuestionASTNode) n);
                continue;
            }

            IfASTNode ifASTNode = (IfASTNode) n;

            boolean cond = ifASTNode.checkConditions();
            System.out.println("Conditions = " + cond);

            visQuestion.addAll(ifASTNode.getQuestionNodes());
        }

        return visQuestion;
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

    @Override
    public void accept(ActionEvent actionEvent) {
        System.out.println("Redraw tree yo");
    }
}
