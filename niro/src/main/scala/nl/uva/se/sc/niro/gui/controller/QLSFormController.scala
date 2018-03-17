package nl.uva.se.sc.niro.gui.controller

import java.io.IOException

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.{ Button, Label }
import javafx.scene.layout.BorderPane
import nl.uva.se.sc.niro.gui.application.QLScenes
import nl.uva.se.sc.niro.gui.control.{ Component, ComponentFactory }
import nl.uva.se.sc.niro.gui.factory.PageVisibilityFactory
import nl.uva.se.sc.niro.model.ql.QLForm
import nl.uva.se.sc.niro.model.ql.expressions.answers.StringAnswer
import nl.uva.se.sc.niro.model.qls.{ QLStylesheet, Question }
import nl.uva.se.sc.niro.util.StringUtil

import scala.collection.JavaConverters

class QLSFormController extends QLFormController  {
  private var stylesheet: Option[QLStylesheet] = None
  private var page: Int = 0

  @FXML var pageName: Label = _

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
  override def cancel(event: ActionEvent): Unit =
    switchToScene(QLScenes.getHomeSceneFileName(), new QLSHomeController())

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

  override def componentChanged(component: Component[_]): Unit = {
    logger.debug(s"Component [${component.getQuestionId}] changed its value to [${component.getValue}]")
    dictionary(component.getQuestionId) = component.getValue
    evaluateAnswers()
    updateView()
  }

  def initializeForm(form: QLForm, stylesheet: Option[QLStylesheet]): Unit = {
    this.qlForm = form
    this.stylesheet = stylesheet

    // FIXME
//    guiForm = ModelConverter.convert(this.qlForm, stylesheet)
    formName.setText(guiForm.name)

    questions = guiForm.questions.map(ComponentFactory.make)
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
