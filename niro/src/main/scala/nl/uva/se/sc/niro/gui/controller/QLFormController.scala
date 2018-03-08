package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.gui._
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.gui.converter.ModelConverter
import nl.uva.se.sc.niro.gui.widget.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.model.QLForm
import nl.uva.se.sc.niro.model.expressions.answers.{ Answer, BooleanAnswer }
import nl.uva.se.sc.niro.model.gui.GUIForm

import scala.collection.{ JavaConverters, mutable }

class QLFormController extends QLBaseController with ModelUpdater {
  private val dictionary = mutable.Map[String, Answer]()
  private var qlForm: QLForm = _
  private var guiForm: GUIForm = _
  private var questions: Seq[Component] = _

  @FXML var formName: Label = _
  @FXML var questionArea: VBox = _

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit =
    QLForms.openHomeScreen(getActiveStage(event))

  @FXML def saveData(event: ActionEvent): Unit =
    // TODO Implement
    println("Data is saved....")

  override def updateModel(questionId: String, answer: Answer): Unit = {
    dictionary(questionId) = answer
    evaluateAnswers
    updateView
  }

  def populateForm(form: QLForm): Unit = {
    this.qlForm = form
    guiForm = ModelConverter.convert(this.qlForm)
    questions = guiForm.questions.map(ComponentFactory.make)

    formName.setText(guiForm.name)
    questionArea.getChildren.addAll(JavaConverters.seqAsJavaList(questions))

    evaluateAnswers
    updateView
  }

  private def evaluateAnswers(): Unit = {
    dictionary ++= Evaluator.evaluate(qlForm, dictionary.toMap)
  }

  private def updateView = {
    updateQuestionControls()
    updateVisibility()
  }

  private def updateQuestionControls(): Unit = {
    questions.foreach(_.update(dictionary))
  }

  private def updateVisibility(): Unit = {
    guiForm.questions.foreach(question => {
      Evaluator.evaluateExpression(question.visibility, qlForm.symbolTable, dictionary.toMap) match {
        case b: BooleanAnswer => question.component.foreach(_.setVisible(b.possibleValue.getOrElse(false)))
        case _                => throw new IllegalArgumentException("A if-condition did not result in a boolean expression!")
      }
    })
  }

}
