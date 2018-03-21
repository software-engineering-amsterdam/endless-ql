package tool;

import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import antlr.qls.StylesheetLexer;
import antlr.qls.StylesheetParser;
import domain.model.ast.ConditionNode;
import domain.model.ast.FormNode;
import domain.Utilities;
import domain.model.ast.ASTNode;
import domain.model.ast.QuestionNode;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QL.QLLoader;
import loader.QLS.QLSLoader;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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
    private TabPane tpPages;

    @FXML
    private Button btnBuild;

    @FXML
    private Label lblErrorField;

    private List<ListView> listViews = new ArrayList<>();

    private boolean qlsEnabled = false;
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
        String qlsSource = taSourceCodeQLS.getText();
        this.qlsEnabled = false;
        this.tpPages.getTabs().clear();
        this.listViews.clear();
        if(qlSource.isEmpty()){
            showAlertBox("Please import or add QL code");
            return;
        }

        if (!qlSource.isEmpty() && qlsSource.isEmpty()){
            generateQL(qlSource);
            buildQL();
        }
        if (!qlSource.isEmpty() && !qlsSource.isEmpty()){
            this.qlsEnabled = true;
            generateQL(qlSource);
            generateQLS(qlsSource);
            buildQLS();
        }
        printInfoMessage("Build successful");
    }
    private void generateQL(String qlSource){
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
    }
    private void buildQL(){
        ListView lvQuestionnaire = new ListView();
        lvQuestionnaire.getItems().clear();
        List<ASTNode> astNodes = this.formNode.getASTNodes();
        List<QuestionNode> questions = getAllQuestions(astNodes);
        drawQuestions(questions,lvQuestionnaire, true);
        listViews.add(lvQuestionnaire);
        Tab t = new Tab("QL Form");
        t.setContent(lvQuestionnaire);
        this.tpPages.getTabs().add(t);
    }
    private void buildQLS(){
        Tab tab;

        for (Page p : this.formNode.getStylesheet().getPages()){
            tab = new Tab(p.getLabel());
            this.tpPages.getTabs().add(tab);
            drawPage(tab, p);
        }
    }
    private void drawPage(Tab tab, Page p){
        HBox hbox = new HBox();
        ListView lv = new ListView<Row>();
        for (Section s : p.getSections()){
            drawSection(s, lv);
        }
        listViews.add(lv);
        hbox.setHgrow(lv, Priority.ALWAYS);
        hbox.getChildren().add(lv);
        tab.setContent(hbox);
    }
    private void generateQLS(String qlsSource){
        CharStream qlsStream = CharStreams.fromString(qlsSource);
        StylesheetLexer qlsLexer = new StylesheetLexer(qlsStream);

        StylesheetParser qlsParser = new StylesheetParser(new CommonTokenStream(qlsLexer));

        //parser.setErrorHandler(new BailErrorStrategy());
        qlsParser.addErrorListener(new ToolBarErrorListener(lblErrorField));

        StylesheetParser.StylesheetBuilderContext stylesheetTree= qlsParser.stylesheetBuilder();
        QLSLoader qlsLoader = new QLSLoader(this.formNode);
        ParseTreeWalker.DEFAULT.walk(qlsLoader, stylesheetTree);
    }

    private void printInfoMessage(String message){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        lblErrorField.setTooltip(new Tooltip(sdf.format(cal.getTime())));
        lblErrorField.setText(message);
    }

    private void drawQuestions(List<QuestionNode> questionNodes, ListView lView, boolean clearView){
        Visitor uiVisitor = new UIVisitor();
        if(clearView){
            lView.getItems().clear();
        }

        this.formNode.evaluateIfs();

        for(QuestionNode qn : questionNodes){
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

        JavaFxObservable.updatesOf(lView.getItems()).subscribe(rows -> System.out.println("R "+rows));
    }
    private void drawSection(Section s, ListView lView){
        Row r = new SectionRow(s.getLabel());
        lView.getItems().add(r);
        List<QuestionNode> temp = new ArrayList<>();
        for (Variable v : s.getVariables()){
            temp.add(this.formNode.getQuestionByVariableIdentifier(v.getIdentifier()));
        }
        drawQuestions(temp, lView, false);

    }

    private List<QuestionNode> getAllQuestions(List<ASTNode> nodes){
        List<QuestionNode> visQuestion = new ArrayList<>();
        for(ASTNode n : nodes){

            if(n instanceof QuestionNode){
                visQuestion.add((QuestionNode) n);
                continue;
            }

            ConditionNode conditionNode = (ConditionNode) n;

            visQuestion.addAll(conditionNode.getQuestionNodes());
            visQuestion.addAll(conditionNode.getElseNodes());
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
    private void redrawAll(){
        List<QuestionNode> questions = getAllQuestions(this.formNode.getASTNodes());
        if (!qlsEnabled){
            drawQuestions(questions, this.listViews.get(0), true);
        }else{
            for (int i = 0; i < this.tpPages.getTabs().size(); i ++){
                Tab tab = this.tpPages.getTabs().get(i);
                Page page = this.formNode.getStylesheet().getPages().get(i);
                drawPage(tab, page);
            }
        }
    }

    @Override
    public void accept(Object event) {
        System.out.println("Redraw tree yo");
        this.redrawAll();
    }
}
