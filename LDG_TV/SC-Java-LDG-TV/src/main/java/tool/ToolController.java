package tool;

import domain.model.ast.ConditionNode;
import domain.model.ast.FormNode;
import domain.Utilities;
import domain.model.ast.ASTNode;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import loader.QL.QLBuilder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class ToolController implements Consumer {

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
     * @param event that kicked of the invocation
     */
    public void generateQuestionnaire(ActionEvent event) {
        this.tpPages.getTabs().clear();
        this.listViews.clear();

        ToolBarErrorListener tbErrorListener = new ToolBarErrorListener(lblErrorField);

        QLBuilder qlBuilder = new QLBuilder(tbErrorListener);
        Utilities.ofEmptyString(taSourceCodeQL.getText())
                .ifPresentOrElse(
                        qlText -> this.formNode = qlBuilder.toFormNode(qlText),
                        () -> showAlertBox("Please import or add QL code")
                );

        Utilities.ofEmptyString(taSourceCodeQLS.getText())
                .ifPresentOrElse(
                        qlsText -> {
                            Stylesheet ss = qlBuilder.toStylesheet(qlsText, this.formNode);
                            this.formNode.setStylesheet(ss);
                            this.qlsEnabled = true;
                            buildQLS();
                        },
                        this::buildQL
                );

        printInfoMessage("Build successful");
    }

    /**
     * Invoked by the 'Import' button action, import .QL or .QLS file
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
                    printInfoMessage("Import " + selectedFile.getName() + " successful");
                },
                () -> showAlertBox("Could not read file.")
        );
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
        Stylesheet styleSheet = formNode.getStylesheet();

        for (Page p : styleSheet.getPages()){
            Tab tab = new Tab(p.getLabel());
            this.tpPages.getTabs().add(tab);
            drawPage(tab, p);
        }
    }
    private void drawPage(Tab tab, Page p){
        HBox hbox = new HBox();
        ListView<Row> lv = new ListView<>();
        for (Section s : p.getSections()){
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
        for (Variable v : s.getVariables()){
            temp.add(this.formNode.getQuestionByVariableIdentifier(v.getIdentifier()));
        }
        drawQuestions(temp, lView, false);

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
                        .filter(b -> !b)
                        .subscribe(this::accept);
            }

            Row r = new QuestionRow(questionText, n);
            r.setDisable(qn.isDisabled());
            lView.getItems().add(r);
        }
    }
    private List<QuestionNode> getAllQuestions(List<ASTNode> nodes){
        List<QuestionNode> visibleQuestions = new ArrayList<>();
        for(ASTNode n : nodes){

            if(n instanceof QuestionNode){
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
        this.redrawAll();
    }
}
