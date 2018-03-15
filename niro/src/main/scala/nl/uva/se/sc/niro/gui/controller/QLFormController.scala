package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{ Button, Label }
import javafx.scene.layout.{ BorderPane, VBox }
import nl.uva.se.sc.niro.Evaluator
import nl.uva.se.sc.niro.gui.application.QLForms
import nl.uva.se.sc.niro.gui.control.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.gui.converter.ModelConverter
import nl.uva.se.sc.niro.gui.factory.PageVisibilityFactory
import nl.uva.se.sc.niro.gui.listener.ComponentChangedListener
import nl.uva.se.sc.niro.model.gui.{ GUIForm, GUIQuestion }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.{ Answer, BooleanAnswer, StringAnswer }
import nl.uva.se.sc.niro.model.qls.{ QLStylesheet, Question }
import nl.uva.se.sc.niro.util.StringUtil

import scala.collection.{ JavaConverters, mutable }

class QLFormController extends QLBaseController with ComponentChangedListener {
  private val dictionary = mutable.Map[String, Answer]()
  private var qlForm: QLForm = _
  private var guiForm: GUIForm = _
  private var questions: Seq[Component[_]] = _
  private var stylesheet: Option[QLStylesheet] = None
  private var page: Int = 0

  @FXML var formName: Label = _
  @FXML var pageName: Label = _
  @FXML var questionArea: VBox = _

  @FXML var navigationBar: BorderPane = _
  @FXML var previous: Button = _
  @FXML var next: Button = _

  @FXML
  def initialize(): Unit = {
    navigationBar.managedProperty().bind(navigationBar.visibleProperty())
    pageName.visibleProperty().bind(navigationBar.visibleProperty())
    pageName.managedProperty().bind(pageName.visibleProperty())
    previous.setDisable(true)
  }

  @FXML
  @throws[IOException]
  def cancel(event: ActionEvent): Unit =
    QLForms.openHomeScreen(getActiveStage(event))

  @FXML
  def saveData(event: ActionEvent): Unit =
    // TODO Implement
    println("Data is saved....")

  @FXML
  def previousPage(event: ActionEvent): Unit = {
    page -= 1
    previous.setDisable(page == 0)
    next.setDisable(false)
    updateView()
  }

  @FXML
  def nextPage(event: ActionEvent): Unit = {
    page += 1
    next.setDisable(page >= stylesheet.map(_.pages.size).getOrElse(0) - 1)
    previous.setDisable(false)
    updateView()
  }

  def componentChanged(component: Component[_]): Unit = {
    dictionary(component.getQuestionId) = component.getValue
    evaluateAnswers()
    updateView()
  }

  def initializeForm(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    this.qlForm = form
    this.stylesheet = stylesheet

    guiForm = ModelConverter.convert(this.qlForm, stylesheet)
    formName.setText(guiForm.name)

    questions = guiForm.questions.map(ComponentFactory.make)
    questions.foreach(_.addComponentChangedListener(this))

    questionArea.getChildren.addAll(JavaConverters.seqAsJavaList(questions))

    navigationBar.setVisible(stylesheet.exists(_.pages.nonEmpty))
    next.setDisable(stylesheet.map(_.pages.size).getOrElse(0) <= 1)

    evaluateAnswers()
    updateView()
  }

  private def evaluateAnswers(): Unit = {
    dictionary ++= Evaluator.evaluate(qlForm, dictionary.toMap)
  }

  private def updateView(): Unit = {
    updatePageTitle()
    updateValues()
    updateOrder()
    updateVisibility()
  }

  private def updatePageTitle(): Unit = {
    val activePageName = getActivePageName
    pageName.setText(StringUtil.addSpaceOnCaseChange(activePageName))
    dictionary(PageVisibilityFactory.ACTIVE_PAGE_NAME) = StringAnswer(activePageName)
  }

  private def getActivePageName: String = {
    stylesheet.map(_.pages(page).name).getOrElse("")
  }

  private def updateValues(): Unit = {
    questions.foreach(_.updateValue(dictionary))
  }

  private def updateOrder(): Unit = {
    stylesheet.map(_.collectQuestionsForPage(getActivePageName)) match {
      case Some(questions) => reorderComponents(questions)
      case _               => ()
    }
  }

  private def reorderComponents(orderedQuestions: Seq[Question]): Unit = {
    orderedQuestions.reverse.foreach(orderedQuestion => {
      val componentsToReOrder = questions.filter(_.getQuestionId == orderedQuestion.name).reverse
      componentsToReOrder.foreach(component => {
        questionArea.getChildren.remove(component)
        questionArea.getChildren.add(0, component)
      })
    })
  }

  private def updateVisibility(): Unit = {
    guiForm.questions.foreach(question => {
      getVisibilitySetting(question) match {
        case visibility: BooleanAnswer => question.component.foreach(_.setVisible(isVisible(visibility)))
        case _                         => throw new IllegalArgumentException("A if-condition did not result in a boolean expression!")
      }
    })
  }

  private def isVisible(b: BooleanAnswer) = {
    b.possibleValue.getOrElse(false)
  }

  private def getVisibilitySetting(question: GUIQuestion) = {
    Evaluator.evaluateExpression(question.visibility, qlForm.symbolTable, dictionary.toMap)
  }
}
