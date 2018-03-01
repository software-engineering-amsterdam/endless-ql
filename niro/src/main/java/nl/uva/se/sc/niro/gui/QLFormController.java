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
import java.util.concurrent.Semaphore;


public class QLFormController extends QLBaseController implements ModelUpdater {
    private Semaphore updateInProgress = new Semaphore(1);
    private QLForm form;
    @FXML
    private Label formName;
    @FXML
    private GridPane questionsGrid;

    @FXML
    public void cancel(ActionEvent event) throws IOException {
        QLForms.openHomeScreen(getActiveStage(event));
    }

    @Override
    public void updateModel(String questionId, Answer answer) {
        if (noUpdateInProgress()) {
            QLForm evaluateQLForm = Evaluator.evaluateQLForm(this.form.save(questionId, answer));
            updateGUI(evaluateQLForm);
        }
    }

    @FXML
    public void saveData(ActionEvent event) {
        System.out.println("Data is saved....");
    }

    public void populateForm(QLForm form) {
        formName.setText(form.formName().replaceAll("(\\p{Ll})(\\p{Lu})","$1 $2"));
        questionsGrid.setPadding(new Insets(0, 20, 0, 20));
        GUICreationVisitor.visit(questionsGrid, form.statements(), form.symbolTable());
        CreateCallbackVisitor.visit(this, questionsGrid, form.statements());
        updateGUI(Evaluator.evaluateQLForm(form));
    }

    private void updateGUI(QLForm form) {
        if (updateInProgress.tryAcquire()) {
            try {
                GUIUpdateVisitor.visit(questionsGrid, form.statements(), form.symbolTable());
                this.form = form;
            } finally {
                updateInProgress.release();
            }
        }
    }

    private boolean noUpdateInProgress() {
        return updateInProgress.availablePermits() > 0;
    }
}
