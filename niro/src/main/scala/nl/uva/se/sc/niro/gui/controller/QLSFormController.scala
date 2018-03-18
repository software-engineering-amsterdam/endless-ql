package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.{ Button, Label }
import javafx.scene.layout.BorderPane
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.converter.{ ModelConverter, StyleDecorator }
import nl.uva.se.sc.niro.gui.factory.{ PageVisibilityFactory, QLComponentFactory }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.StringAnswer
import nl.uva.se.sc.niro.model.qls.{ QLStylesheet, Question }
import nl.uva.se.sc.niro.util.StringUtil

import scala.collection.JavaConverters

class QLSFormController extends QLFormController {
  private var stylesheet: Option[QLStylesheet] = None
  private var page: Int = 0

  val pageName: Label = new Label

  val navigationBar: BorderPane = new BorderPane
  val previous: Button = new Button
  val next: Button = new Button

  @FXML
  def initialize(): Unit = {
    applyStylingToPageName()
    topBox.getChildren.add(pageName)

    addButtonsToNavigationBar()
    applyStylingToNavigationBar()
    // Place above existing buttons
    bottomBox.getChildren.add(0, navigationBar)

    navigationBar.managedProperty().bind(navigationBar.visibleProperty())
    pageName.visibleProperty().bind(navigationBar.visibleProperty())
    pageName.managedProperty().bind(pageName.visibleProperty())
  }

  @FXML
  @throws[IOException]
  override def cancel(event: ActionEvent): Unit =
    switchToScene(QLScenes.homeScene, new QLSHomeController())

  def previousPage(event: ActionEvent): Unit = {
    page -= 1
    previous.setDisable(page == 0)
    next.setDisable(false)
    updateView()
  }

  def nextPage(event: ActionEvent): Unit = {
    page += 1
    next.setDisable(page >= stylesheet.map(_.pages.size).getOrElse(0) - 1)
    previous.setDisable(false)
    updateView()
  }

  def applyStylingToPageName(): Unit = {
    pageName.setPadding(new Insets(10.0, 0.0, 0.0, 0.0))
    pageName.getStyleClass.add("-fx-font-size: 18pt;")
  }

  def addButtonsToNavigationBar(): Unit = {
    previous.setText("Previous")
    previous.setDisable(true)
    previous.setOnAction(previousPage)
    navigationBar.setLeft(previous)

    next.setText("Next")
    next.setOnAction(nextPage)
    navigationBar.setRight(next)
  }

  def applyStylingToNavigationBar(): Unit = {
    navigationBar.setPadding(new Insets(0.0, 10.0, 0.0, 10.0))
  }

  def initializeForm(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    this.qlForm = form
    this.stylesheet = stylesheet

    guiForm = ModelConverter.convert(this.qlForm)
    guiForm = stylesheet.map(stylesheet => StyleDecorator.applyStyle(guiForm, stylesheet)).getOrElse(guiForm)

    formName.setText(guiForm.name)
    questions = guiForm.questions.map(QLComponentFactory.make)
    questions.foreach(_.addComponentChangedListener(this))

    questionArea.getChildren.addAll(JavaConverters.seqAsJavaList(questions))

    navigationBar.setVisible(stylesheet.exists(_.pages.nonEmpty))
    next.setDisable(stylesheet.map(_.pages.size).getOrElse(0) <= 1)

    evaluateAnswers()
    updateView()
  }

  override def updateView(): Unit = {
    updatePageTitle()
    updateValues()
    updateOrder()
    updateVisibility()
  }

  def updatePageTitle(): Unit = {
    val activePageName = getActivePageName
    pageName.setText(StringUtil.addSpaceOnCaseChange(activePageName))
    // TODO Check if this is a logical location for this...
    dictionary(PageVisibilityFactory.ACTIVE_PAGE_NAME) = StringAnswer(activePageName)
  }

  def getActivePageName: String = {
    stylesheet.map(_.pages(page).name).getOrElse("")
  }

  def updateOrder(): Unit = {
    stylesheet.map(_.collectQuestionsForPage(getActivePageName)) match {
      case Some(questionToReorder) => reorderComponents(questionToReorder)
      case _                       => ()
    }
  }

  def reorderComponents(orderedQuestions: Seq[Question]): Unit = {
    orderedQuestions.reverse.foreach(orderedQuestion => {
      val componentsToReOrder = questions.filter(_.getQuestionId == orderedQuestion.name).reverse
      componentsToReOrder.foreach(component => {
        questionArea.getChildren.remove(component)
        questionArea.getChildren.add(0, component)
      })
    })
  }

}
