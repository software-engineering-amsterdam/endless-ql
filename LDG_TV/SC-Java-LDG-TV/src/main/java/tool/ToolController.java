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
import javafx.event.ActionEvent;
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

        List<QuestionASTNode> questions = getAllVisibleQuestions(astNodes);


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
            lvQuestionnaire.getItems().add(new QuestionRow(questionText, n, false));
        }
    }

    private List<QuestionASTNode> getAllVisibleQuestions(List<ASTNode> nodes){
        List<QuestionASTNode> visQuestion = new ArrayList<>();
        for(ASTNode n : nodes){
            if(!n.isVisible()) {
                continue;
            }

            if(n instanceof QuestionASTNode){
                visQuestion.add((QuestionASTNode) n);
                continue;
            }

            IfASTNode ifASTNode = (IfASTNode) n;
            visQuestion.addAll(ifASTNode.getQuestionNodes());
        }

        return visQuestion;
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
