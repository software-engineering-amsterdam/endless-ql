package nl.uva.se.sc.niro.gui.controllers

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.gui._
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.gui.converters.ModelConverter
import nl.uva.se.sc.niro.gui.widgets.ComponentFactory
import nl.uva.se.sc.niro.model.QLForm
import nl.uva.se.sc.niro.model.expressions.Answer
import nl.uva.se.sc.niro.model.gui.GUIForm

import scala.collection.JavaConverters

class QLFormController extends QLBaseController with ModelUpdater {
  @FXML var formName: Label = _
  @FXML var questions: VBox = _
  private var qlForm: QLForm = _
  private var guiForm: GUIForm = _

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit =
    QLForms.openHomeScreen(getActiveStage(event))

  override def updateModel(questionId: String, answer: Answer): Unit =
    updateGUI(Evaluator.evaluate(qlForm.save(questionId, answer)))

  @FXML def saveData(event: ActionEvent): Unit =
    println("Data is saved....")

  def populateForm(form: QLForm): Unit = {
    this.qlForm = form
    guiForm = ModelConverter.convert(this.qlForm)

    formName.setText(guiForm.name)

//    questions.setPadding(new Insets(0, 20, 0, 20))
    val guiQuestions = guiForm.questions.map(ComponentFactory.make)
    questions.getChildren.addAll(JavaConverters.seqAsJavaList(guiQuestions))
//    CreateCallbackVisitor.visit(this, questionsGrid, form.statements)
//    updateGUI(Evaluator.evaluate(form))
  }

  private def updateGUI(form: QLForm) = {
//    GUIUpdateVisitor.visit(questionsGrid, form.statements, form.symbolTable)
    this.qlForm = form
  }

}
