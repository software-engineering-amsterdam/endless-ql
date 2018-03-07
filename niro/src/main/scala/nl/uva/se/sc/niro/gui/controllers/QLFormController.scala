package nl.uva.se.sc.niro.gui.controllers

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.GridPane
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.gui._
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.model.expressions.Answer
import nl.uva.se.sc.niro.model.QLForm

class QLFormController extends QLBaseController with ModelUpdater {
  private var form: QLForm = null
  @FXML private var formName: Label = null
  @FXML private var questionsGrid: GridPane = null

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit = QLForms.openHomeScreen(getActiveStage(event))

  override def updateModel(questionId: String, answer: Answer): Unit =
    updateGUI(Evaluator.evaluate(form.save(questionId, answer)))

  @FXML def saveData(event: ActionEvent): Unit = System.out.println("Data is saved....")

  def populateForm(form: QLForm): Unit = {
    formName.setText(form.formName.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2"))
    questionsGrid.setPadding(new Insets(0, 20, 0, 20))
    GUICreationVisitor.visit(questionsGrid, form.statements, form.symbolTable)
    CreateCallbackVisitor.visit(this, questionsGrid, form.statements)
    updateGUI(Evaluator.evaluate(form))
  }

  private def updateGUI(form: QLForm) = {
    GUIUpdateVisitor.visit(questionsGrid, form.statements, form.symbolTable)
    this.form = form
  }

}
