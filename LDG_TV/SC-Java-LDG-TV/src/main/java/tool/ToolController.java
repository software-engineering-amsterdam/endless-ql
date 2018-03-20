package tool;

import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import antlr.qls.StylesheetLexer;
import antlr.qls.StylesheetParser;
import domain.model.ast.FormNode;
import domain.Utilities;
import domain.model.ast.ASTNode;
import domain.model.ast.IfASTNode;
import domain.model.ast.QuestionASTNode;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
import domain.model.stylesheet.Stylesheet;
import domain.model.variable.Variable;
import domain.visitor.UIVisitor;
import domain.visitor.Visitor;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QL.QLLoader;
import loader.QLS.QLSLoader;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class ToolController implements Initializable, Consumer {

    @FXML
    private TextArea taSourceCodeQL;

    @FXML
    private TextArea taSourceCodeQLS;

    @FXML
    private ListView<Row> lvQuestionnaire;

    @FXML
    private TabPane tpPages;

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

                lvQuestionnaire.getItems().clear();

        // Parse input field and create AST
        CharStream stream = CharStreams.fromString(qlSource);
        FormLexer lexer = new FormLexer(stream);

        FormParser parser = new FormParser(new CommonTokenStream(lexer));

        //parser.setErrorHandler(new BailErrorStrategy());
        parser.addErrorListener(new ToolBarErrorListener(lblErrorField));

        FormParser.FormBuilderContext tree = parser.formBuilder();
        QLLoader loader = new QLLoader();
        ParseTreeWalker.DEFAULT.walk(loader, tree);

        this.formNode = loader.getFormNode();

        if(!qlsSource.isEmpty()){
            CharStream qlsStream = CharStreams.fromString(qlsSource);
            StylesheetLexer qlsLexer = new StylesheetLexer(qlsStream);

            StylesheetParser qlsParser = new StylesheetParser(new CommonTokenStream(qlsLexer));

            //parser.setErrorHandler(new BailErrorStrategy());
            qlsParser.addErrorListener(new ToolBarErrorListener(lblErrorField));

            StylesheetParser.StylesheetBuilderContext stylesheetTree= qlsParser.stylesheetBuilder();
            QLSLoader qlsLoader = new QLSLoader(this.formNode);
            ParseTreeWalker.DEFAULT.walk(qlsLoader, stylesheetTree);
            drawPages(this.formNode.getStylesheet());
        }


        List<ASTNode> astNodes = this.formNode.getASTNodes();

        List<QuestionASTNode> questions = getAllQuestions(astNodes);
        drawQuestions(questions, lvQuestionnaire);
        printInfoMessage("Build successful");
    }

    private void printInfoMessage(String message){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        lblErrorField.setTooltip(new Tooltip(sdf.format(cal.getTime())));
        lblErrorField.setText(message);
    }

    private void drawQuestions(List<QuestionASTNode> questionASTNodes, ListView lView){
        Visitor uiVisitor = new UIVisitor();
        lView.getItems().clear();

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
            lView.getItems().add(r);
        }
    }

    private void drawPages(Stylesheet stylesheet){
        ListView lv;
        Label sl;
        Tab tab;
        Pane pane;
        for (Page p : stylesheet.getPages()){
            tab = new Tab(p.getLabel());
            this.tpPages.getTabs().add(tab);
            lv = new ListView<Row>();
            for (Section s : p.getSections()){
                drawQuestions(s.getQuestions(), lv);
            }
            tab.setContent(lv);
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
                    printInfoMessage("Import "+ selectedFile.getName() +" successful");
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
                    printInfoMessage("Import "+ selectedFile.getName() +" successful");
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
        drawQuestions(questions, lvQuestionnaire);
    }
}
