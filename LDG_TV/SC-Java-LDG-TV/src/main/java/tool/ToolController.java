package tool;

import domain.Utilities;
import domain.model.ast.ASTNode;
import domain.model.ast.ConditionNode;
import domain.model.ast.FormNode;
import domain.model.ast.QuestionNode;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
import domain.model.stylesheet.Stylesheet;
import domain.model.variable.Variable;
import domain.visitor.UIVisitor;
import domain.visitor.Visitor;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QL.LoaderErrorListener;
import loader.QL.QLBuilder;
import loader.QLS.QLSBuilder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ToolController implements Consumer, LoaderErrorListener {

    @FXML
    private TextArea taSourceCodeQL;

    @FXML
    private TextArea taSourceCodeQLS;

    @FXML
    private TabPane tpPages;

    @FXML
    private Label lblErrorField;

    private List<ListView<Row>> listViews = new ArrayList<>();

    private boolean qlsEnabled = false;
    private FormNode formNode = null;

    /**
     * Invoked by the 'build' button action, to generate the questionnaire based on the written QL
     *
     * @param event that kicked of the invocation
     */
    public void generateQuestionnaire(ActionEvent event) {
        this.tpPages.getTabs().clear();
        this.listViews.clear();

        ToolBarErrorListener tbErrorListener = new ToolBarErrorListener(lblErrorField);
        DialogErrorListener dialogErrorListener = new DialogErrorListener();

        QLBuilder qlBuilder = new QLBuilder(tbErrorListener, dialogErrorListener);

        Utilities.ofEmptyString(taSourceCodeQL.getText())
                .ifPresentOrElse(
                        qlText -> parseQLFromText(qlBuilder, qlText),
                        () -> showAlertBox("Please import or add QL code")
                );

        QLSBuilder qlsBuilder = new QLSBuilder(tbErrorListener, dialogErrorListener);
        Utilities.ofEmptyString(taSourceCodeQLS.getText())
                .ifPresentOrElse(
                        qlsText -> parseStyleSheetFromText(qlsBuilder, qlsText),
                        this::buildQL
                );

        printInfoMessage("Build ended");
    }

    private void parseQLFromText(QLBuilder qlBuilder, String qlText) {
        FormNode fn = qlBuilder.toFormNode(qlText);
        boolean fnIsSet = fn != null;

        if(fnIsSet){
            this.formNode = fn;
        } else {
            showAlertBox("Error on parsing QL");
        }
    }

    private void parseStyleSheetFromText(QLSBuilder qlsBuilder, String qlsText) {
        Stylesheet ss = qlsBuilder.toStylesheet(qlsText, this.formNode);
        boolean ssIsSet = ss != null;
        if(ssIsSet) {
            this.formNode.setStylesheet(ss);
            this.qlsEnabled = true;
            buildQLS();
        } else {
            showAlertBox("Error on parsing QLS");
        }
    }

    /**
     * Invoked by the 'Import' button action, import .QL or .QLS file
     *
     * @param event that kicked of the invocation
     */
    public void importQLFile(ActionEvent event) {
        Optional<String> text = getTextFromFile();

        text.ifPresentOrElse(
                t -> {
                    taSourceCodeQL.setText(t);
                    printInfoMessage("Import QL successful");
                },
                () -> showAlertBox("Could not read file.")
        );
    }

    public void importQLSFile(ActionEvent event) {
        Optional<String> text = getTextFromFile();

        text.ifPresentOrElse(
                t -> {
                    taSourceCodeQLS.setText(t);
                    printInfoMessage("Import QLS successful");
                },
                () -> showAlertBox("Could not read file.")
        );
    }

    private Optional<String> getTextFromFile(){
        FileChooser fileChooser = getFileChooser();

        Stage s = new Stage();
        File selectedFile = fileChooser.showOpenDialog(s);

        if (selectedFile == null) {
            return Optional.empty();
        }

        return Utilities.readFile(selectedFile.getAbsolutePath());
    }

    private void buildQL(){
        this.qlsEnabled = false;
        ListView lvQuestionnaire = new ListView();
        List<ASTNode> astNodes = this.formNode.getASTNodes();
        List<QuestionNode> questions = getAllQuestions(astNodes);
        drawQuestions(questions, lvQuestionnaire, true);

        listViews.add(lvQuestionnaire);
        Tab t = new Tab("QL Form");
        t.setContent(lvQuestionnaire);
        this.tpPages.getTabs().add(t);
    }

    private void buildQLS(){
        Stylesheet styleSheet = formNode.getStylesheet();

        for (Page p : styleSheet.getPages()) {
            Tab tab = new Tab(p.getLabel());
            this.tpPages.getTabs().add(tab);
            drawPage(tab, p);
        }
    }

    private void drawPage(Tab tab, Page p){
        HBox hbox = new HBox();
        ListView<Row> lv = new ListView<>();
        for (Section s : p.getSections()) {
            drawSection(s, lv);
        }
        listViews.add(lv);
        HBox.setHgrow(lv, Priority.ALWAYS);
        hbox.getChildren().add(lv);
        tab.setContent(hbox);
    }

    private void drawSection(Section s, ListView<Row> lView){
        Row r = new SectionRow(s.getLabel());
        lView.getItems().add(r);
        List<QuestionNode> temp = new ArrayList<>();
        for (Variable v : s.getVariables()) {
            temp.add(this.formNode.getQuestionByVariableIdentifier(v.getIdentifier()));
        }
        drawQuestions(temp, lView, false);
    }

    private void drawQuestions(List<QuestionNode> questionNodes, ListView<Row> lView, boolean clearView){
        Visitor uiVisitor = new UIVisitor();
        if (clearView) {
            lView.getItems().clear();
        }

        this.formNode.evaluateIfs();

        for (QuestionNode qn : questionNodes) {
            String questionText = qn.getText();
            Variable qv = qn.getVariable();

            Node n = qv.getRelatedUIElement(uiVisitor);

            JavaFxObservable.actionEventsOf(n)
                    .subscribe(this::accept);

            if (n instanceof TextField) {
                TextField tf = (TextField) n;

                JavaFxObservable.changesOf(tf.focusedProperty())
                        .map(Change::getNewVal)
                        .filter(b -> !b)
                        .subscribe(this::accept);
            }

            Row r = new QuestionRow(questionText, n);
            r.setDisable(qn.isDisabled());
            lView.getItems().add(r);
        }
    }

    private List<QuestionNode> getAllQuestions(List<ASTNode> nodes) {
        List<QuestionNode> visibleQuestions = new ArrayList<>();
        for (ASTNode n : nodes) {

            if (n instanceof QuestionNode) {
                visibleQuestions.add((QuestionNode) n);
                continue;
            }

            ConditionNode conditionNode = (ConditionNode) n;

            visibleQuestions.addAll(conditionNode.getQuestionNodes());
            visibleQuestions.addAll(conditionNode.getElseNode().getQuestionNodes());
        }

        return visibleQuestions;
    }

    private void showAlertBox(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage);
        alert.showAndWait();
    }

    private void printInfoMessage(String message){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        lblErrorField.setTooltip(new Tooltip(sdf.format(cal.getTime())));
        lblErrorField.setText(message);
    }

    private FileChooser getFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open QL File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Questionnaire Language File (*.ql)", "*.ql"),
                new FileChooser.ExtensionFilter("Questionnaire Stylesheet Language File (*.qls)", "*.qls"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        return fileChooser;
    }

    private void redrawAll(){
        List<QuestionNode> questions = getAllQuestions(this.formNode.getASTNodes());
        if (!qlsEnabled) {
            drawQuestions(questions, this.listViews.get(0), true);
            return;
        }

        for (int i = 0; i < this.tpPages.getTabs().size(); i ++){
            Tab tab = this.tpPages.getTabs().get(i);
            Page page = this.formNode.getStylesheet().getPages().get(i);
            drawPage(tab, page);
        }
    }

    @Override
    public void accept(Object event) {
        this.redrawAll();
    }

    @Override
    public void onError(String message) {
        this.showAlertBox(message);
    }
}
