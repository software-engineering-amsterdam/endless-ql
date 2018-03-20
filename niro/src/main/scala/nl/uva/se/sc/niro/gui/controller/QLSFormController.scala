package nl.uva.se.sc.niro.gui.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.scene.control.{ Button, Label }
import javafx.scene.layout.BorderPane
import nl.uva.se.sc.niro.gui.converter.{ GUIModelFactory, StyleDecorator }
import nl.uva.se.sc.niro.gui.factory.{ PageVisibilityFactory, QLSComponentFactory }
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.StringAnswer
import nl.uva.se.sc.niro.model.qls.{ QLStylesheet, Question }
import nl.uva.se.sc.niro.util.StringUtil

import scala.collection.JavaConverters

class QLSFormController(homeController: QLHomeController) extends QLFormController(homeController) {
  private var stylesheet: QLStylesheet = _
  private var page: Int = 0

  val pageName: Label = new Label("Page Name")

  val navigationBar: BorderPane = new BorderPane
  val previous: Button = new Button("Previous")
  val next: Button = new Button("Next ")

  override def applicationName(): String = "QLS Forms"

  @FXML
  def initialize(): Unit = {
    applyStylingToPageName()
    topBox.getChildren.add(pageName)

    addButtonsToNavigationBar()
    applyStylingToNavigationBar()
    // Place above existing buttons
    bottomBox.getChildren.add(0, navigationBar)
  }

  def previousPage(event: ActionEvent): Unit = {
    page -= 1
    previous.setDisable(page == 0)
    next.setDisable(false)
    updateView()
  }

  def nextPage(event: ActionEvent): Unit = {
    page += 1
    next.setDisable(page >= stylesheet.pages.size - 1)
    previous.setDisable(false)
    updateView()
  }

  def applyStylingToPageName(): Unit = {
    pageName.setPadding(new Insets(10.0, 0.0, 0.0, 0.0))
    pageName.getStyleClass.add("-fx-font-size: 18pt;")
  }

  def addButtonsToNavigationBar(): Unit = {
    previous.setDisable(true)
    previous.setOnAction(previousPage)
    navigationBar.setLeft(previous)

    next.setOnAction(nextPage)
    navigationBar.setRight(next)
  }

  def applyStylingToNavigationBar(): Unit = {
    navigationBar.setPadding(new Insets(0.0, 10.0, 0.0, 10.0))
  }

  def initializeForm(form: QLForm, stylesheet: QLStylesheet): Unit = {
    this.qlForm = form
    this.stylesheet = stylesheet

    guiForm = StyleDecorator.applyStyle(GUIModelFactory.makeFrom(qlForm), stylesheet)
    questions = guiForm.questions.map(QLSComponentFactory.make)
    questions.foreach(_.addComponentChangedListener(this))
    questionArea.getChildren.addAll(JavaConverters.seqAsJavaList(questions))

    getActiveStage.setTitle("QLS forms")
    formName.setText(guiForm.name)

    next.setDisable(stylesheet.pages.size <= 1)
    evaluateAnswers()
    updateView()
  }

  override def updateView(): Unit = {
    updatePageTitle()
    updateValues()
    updateOrder()
    ()
  }

  def updatePageTitle(): Unit = {
    val activePageName = getActivePageName
    pageName.setText(StringUtil.addSpaceOnCaseChange(activePageName))
    // TODO Check if this is a logical location for this...
    dictionary(PageVisibilityFactory.ACTIVE_PAGE_NAME) = StringAnswer(activePageName)
  }

  def getActivePageName: String = {
    stylesheet.pages(page).name
  }

  def updateOrder(): Unit = {
    reorderComponents(stylesheet.collectQuestionsForPage(getActivePageName))
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
