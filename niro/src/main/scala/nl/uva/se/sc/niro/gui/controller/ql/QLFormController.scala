package nl.uva.se.sc.niro.gui.controller.ql

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{ Alert, ButtonType, Label, ScrollPane }
import javafx.scene.layout.VBox
import javafx.stage.FileChooser
import nl.uva.se.sc.niro.QLFormService
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.component.Component
import nl.uva.se.sc.niro.gui.component.ql.QLComponentFactory
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.gui.widget.ql.QLWidgetFactory
import nl.uva.se.sc.niro.model.gui.ql.{ GUIForm, GUIQuestion }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.evaluation.ExpressionEvaluator._
import nl.uva.se.sc.niro.model.ql.evaluation.QLFormEvaluator
import nl.uva.se.sc.niro.model.ql.expressions.answers.Answer
import org.apache.logging.log4j.scala.Logging

import scala.collection.{ JavaConverters, mutable }

class QLFormController(homeController: QLHomeController, model: QLForm, guiForm: GUIForm)
    extends QLBaseController
    with ComponentChangedListener
    with Logging {

  // TODO align naming!
  type ValueStore = mutable.Map[String, Answer]
  protected val valuesForQuestions: ValueStore = mutable.Map[String, Answer]()
  protected var questionComponents: Seq[Component[_]] = _ // The actual components that handle the user interaction

  @FXML protected var topBox: VBox = _
  @FXML protected var formName: Label = _
  @FXML protected var questionArea: ScrollPane = _

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
      QLFormService.saveMemoryTableToCSV(valuesForQuestions.toMap, file)
      showSavedMessage()
      cancel(event)
    }
  }

  def componentChanged(component: Component[_]): Unit = {
    logger.debug(s"Component [${component.getQuestionId}] changed its value to [${component.getValue}]")
    updateValueForQuestion(component)
    evaluateAnswers()
    updateView()
  }

  def updateValueForQuestion(component: Component[_]): Unit = {
    component.getValue match {
      case Some(answer) => valuesForQuestions += (component.getQuestionId -> answer)
      case None         => valuesForQuestions -= component.getQuestionId
    }
  }

  def initializeForm(): Unit = {
    val questionBox = new VBox()
    questionBox.setPadding(new Insets(0.0, 20.0, 0.0, 20.0))

    questionComponents = guiForm.questions.map(QLComponentFactory(this, new QLWidgetFactory()).make)

    questionBox.getChildren.addAll(JavaConverters.seqAsJavaList(questionComponents))
    questionArea.setContent(questionBox)

    getActiveStage.setTitle("QL forms")
    formName.setText(guiForm.name)

    evaluateAnswers()
    updateView()
  }

  def evaluateAnswers(): Unit = {
    logger.debug(s"Values before evaluation:\n${pprint.apply(valuesForQuestions)}")
    valuesForQuestions ++= QLFormEvaluator.evaluate(model, valuesForQuestions.toMap)
    logger.debug(s"Values after evaluation:\n${pprint.apply(valuesForQuestions)}")
  }

  def updateView(): Unit = {
    updateValues()
    updateVisibility()
  }

  def updateValues(): Unit = {
    questionComponents.foreach(_.updateValue(valuesForQuestions))
  }

  def updateVisibility(): Unit = {
    guiForm.questions.foreach { question =>
      val isQuestionVisible: Boolean = getVisibilitySetting(question)
      question.component.foreach(_.setVisible(isQuestionVisible))
    }
  }

  def getVisibilitySetting(question: GUIQuestion): Boolean = {
    question.visibility.evaluate(model.symbolTable, valuesForQuestions.toMap).exists(_.isTrue)
  }

  def showSavedMessage(): Unit = {
    val alert = new Alert(AlertType.INFORMATION, "The file has successfuly been saved.", ButtonType.OK)
    alert.setTitle("Save results")
    alert.showAndWait()
  }
}