package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import nl.uva.se.sc.niro.gui._
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.gui.converter.ModelConverter
import nl.uva.se.sc.niro.gui.widgets.ComponentFactory
import nl.uva.se.sc.niro.model.QLForm
import nl.uva.se.sc.niro.model.expressions.answers.Answer
import nl.uva.se.sc.niro.model.gui.GUIForm

import scala.collection.{ JavaConverters, mutable }

class QLFormController extends QLBaseController with ModelUpdater {
  @FXML var formName: Label = _
  @FXML var questions: VBox = _
  private var qlForm: QLForm = _
  private var guiForm: GUIForm = _
  // TODO check if 'mutable' in the definition is needed.
  //                          |
  private val symbolTable: mutable.Map[String, Answer] = mutable.Map()

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit =
    QLForms.openHomeScreen(getActiveStage(event))

  override def updateModel(questionId: String, answer: Answer): Unit = {
    symbolTable(questionId) = answer
    // TODO evaluate AST model
    updateComponents()
  }

  @FXML def saveData(event: ActionEvent): Unit =
    // TODO Implement
    println("Data is saved....")

  def populateForm(form: QLForm): Unit = {
    this.qlForm = form
    guiForm = ModelConverter.convert(this.qlForm)

    formName.setText(guiForm.name)

    val guiQuestions = guiForm.questions.map(ComponentFactory.make)
    questions.getChildren.addAll(JavaConverters.seqAsJavaList(guiQuestions))
    updateComponents()
  }

  private def updateComponents(): Unit = {
    // TODO update components
    println("Updating components")
  }

}
