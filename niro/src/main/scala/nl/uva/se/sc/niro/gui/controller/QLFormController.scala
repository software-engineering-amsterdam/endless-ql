package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{ Alert, ButtonType, Label }
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import nl.uva.se.sc.niro.ExpressionEvaluator._
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.control.Component
import nl.uva.se.sc.niro.gui.converter.GUIModelFactory
import nl.uva.se.sc.niro.gui.factory.QLComponentFactory
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.gui.{ GUIForm, GUIQuestion }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, BooleanAnswer }
import nl.uva.se.sc.niro.{ Evaluator, QLFormService }
import org.apache.logging.log4j.scala.Logging

import scala.collection.{ JavaConverters, mutable }

class QLFormController(homeController: QLHomeController)
    extends QLBaseController
    with ComponentChangedListener
    with Logging {

  type ValueStore = mutable.Map[String, Answer]
  protected val dictionary: ValueStore = mutable.Map[String, Answer]()

  protected var qlForm: QLForm = _
  protected var guiForm: GUIForm = _
  protected var questions: Seq[Component[_]] = _
  @FXML protected var topBox: VBox = _

  @FXML protected var formName: Label = _
  @FXML protected var questionArea: VBox = _
  @FXML protected var bottomBox: VBox = _

  override def applicationName(): String = "QL Forms"

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit =
    switchToScene(QLScenes.homeScene, homeController)

  @FXML
  def saveData(event: ActionEvent): Unit = {
    val fileChooser = new FileChooser
    fileChooser.setTitle("Save file")
    fileChooser.getExtensionFilters.add(new FileChooser.ExtensionFilter("CSV", "*.csv"))
    val file = fileChooser.showSaveDialog(getActiveStage)

    if (file != null) {
      QLFormService.saveMemoryTableToCSV(dictionary.toMap, file)
      showSavedMessage()
      cancel(event)
    }
  }

  def componentChanged(component: Component[_]): Unit = {
    logger.debug(s"Component [${component.getQuestionId}] changed its value to [${component.getValue}]")
    dictionary(component.getQuestionId) = component.getValue
    evaluateAnswers()
    updateView()
  }

  def initializeForm(form: QLForm): Unit = {
    this.qlForm = form

    guiForm = GUIModelFactory.makeFrom(this.qlForm)
    questions = guiForm.questions.map(QLComponentFactory.make)
    questions.foreach(_.addComponentChangedListener(this))
    questionArea.getChildren.addAll(JavaConverters.seqAsJavaList(questions))

    getActiveStage.setTitle("QL forms")
    formName.setText(guiForm.name)

    evaluateAnswers()
    updateView()
  }

  def evaluateAnswers(): Unit = {
    logger.debug(s"Values before evaluation:\n${pprint.apply(dictionary)}")
    dictionary ++= Evaluator.evaluate(qlForm, dictionary.toMap)
    logger.debug(s"Values after evaluation:\n${pprint.apply(dictionary)}")
  }

  def updateView(): Unit = {
    updateValues()
    updateVisibility()
  }

  def updateValues(): Unit = {
    questions.foreach(_.updateValue(dictionary))
  }

  private def updateVisibility(): Unit = {
    guiForm.questions.foreach { question =>
      val isQuestionVisible: Boolean = getVisibilitySetting(question)
      question.component.foreach(_.setVisible(isQuestionVisible))
    }
  }

  private def getVisibilitySetting(question: GUIQuestion): Boolean = {
    question.visibility.evaluate(qlForm.symbolTable, dictionary.toMap).exists(_.isTrue)
  }

  def showSavedMessage(): Unit = {
    val alert = new Alert(AlertType.INFORMATION, "The file has successfuly been saved.", ButtonType.OK)
    alert.setTitle("Save results")
    alert.showAndWait()
  }
}
