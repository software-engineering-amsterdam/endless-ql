package nl.uva.se.sc.niro.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import nl.uva.se.sc.niro.Evaluator;
import nl.uva.se.sc.niro.model.Expressions.Answer;
import nl.uva.se.sc.niro.model.QLForm;

import java.io.IOException;


public class QLFormController extends QLBaseController implements ModelUpdater {
    @FXML
    private Label formName;

    @FXML
    private GridPane questionsGrid;

    private QLForm form;

    private transient boolean updateInProgress = false;

    public void populateForm(QLForm form) {
        this.form = form;
        formName.setText(form.formName().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"));
        questionsGrid.setPadding(new Insets(0, 20, 0, 20));
        GUICreationVisitor.visit(questionsGrid, form.statements(), form.symbolTable());
        CreateCallbackVisitor.visit(this, questionsGrid, form.statements());
        updateGUI();
    }

    public void updateGUI() {
        if (!updateInProgress) {
            updateInProgress = true;
            GUIUpdateVisitor.visit(questionsGrid, Evaluator.evaluateQLForm(form).statements(), form.symbolTable());
            updateInProgress = false;
        }
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        QLForms.openHomeScreen(getActiveStage(event));
    }

    @FXML
    public void saveData(ActionEvent event) {
        System.out.println("Data is saved....");
    }

    @Override
    public void updateModel(String questionId, Answer answer) {
        form = form.save(questionId, answer);
        updateGUI();
    }
}
